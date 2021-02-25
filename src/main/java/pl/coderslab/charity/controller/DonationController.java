package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.service.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/donate")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;
    private final ArchivesService archivesService;
    private final MailService mailService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService, UserService userService, ArchivesService archivesService, MailService mailService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.userService = userService;
        this.archivesService = archivesService;
        this.mailService = mailService;
    }

    @ModelAttribute("fullName")
    public String getNickName(Principal principal){
        return userService.getPrincipalName(principal.getName());

    }


    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.getAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }

    @GetMapping("/{institutionId}")
    public String details(@PathVariable long institutionId, Principal principal, Model model){
        List<Donation> donations = donationService.findAllMyDonationForInstitutionSortByDate(institutionId, principal.getName());
        model.addAttribute("donations",donations);
    return "donation/donationDetails";
    }

    @GetMapping("/add-gifts")
    public String addDonation(Model model){
        model.addAttribute("donation", new Donation());
    return "donation/addGift";
    }

    @PostMapping("/add-gifts")
    public String addDonation(@ModelAttribute @Valid Donation donation, BindingResult result, Principal principal){
        if (result.hasErrors()){
            return "donation/addGift";
        }
        Optional<User> optionalUser = userService.findUserByEmail(principal.getName());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            Set<Archives> archivesSet = new HashSet<>();

            Archives archives = new Archives();
            archives.setStatus(Status.PLACED);
            archives.setLocalDate(LocalDate.now());
            archives.setLocalTime(LocalTime.now());
            archivesService.save(archives);

            archivesSet.add(archives);

            donation.setArchives(archivesSet);
            donation.setCreatedDateTime(LocalDateTime.now());
            donation.setStatus(Status.PLACED);
            donationService.save(donation, user);

            sendMail(user, donation);

            return "donation/successAddDonation";
        }
        return "donation/addGift";

    }

    private void sendMail(User user, Donation donation){

        String subject = "Charity! Własnie dodałes dar!";
        String text = "Witaj " + user.getFullName() + "! Twoj dar zostal dodany oraz przyjal status utworzony"
                + "." + "\n";
        String details = "Szczególy: " + "\n Instytucja: " + donation.getInstitution().getName() + "\n Przedmioty: " +
                donation.getCategories().stream().map(Category::getName).collect(Collectors.joining(",")) +
                "\n Ilość worków: " + donation.getQuantity() + "\n Adres: " + donation.getStreet() + " " +donation.getCity() + " " +
                donation.getZipCode() + "\n Data odbioru: " + donation.getPickUpDate() + " " + donation.getPickUpTime() + "\n Szczegoly dla kierowcy: "
                + donation.getPickUpComment();


        try {
            mailService.sendMail(user.getUsername(), subject, text + details, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/charity")
    public String myCharity(Model model, Principal principal){
        model.addAttribute("institutions", donationService.getInstitutionNameByMyDonation(principal.getName()));
        return "donation/myDonations";
    }




}
