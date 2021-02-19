package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/donate")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService, UserService userService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @ModelAttribute("nickName")
    public String getNickName(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        return user.getNickName();

    }


    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.getAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
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
        donationService.save(donation, principal.getName());
    return "donation/successAddDonation";
    }


}
