package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {

    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationService donationService;


    public AppController(UserService userService, InstitutionService institutionService, DonationService donationService) {
        this.userService = userService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionService.findAll();
    }


    @GetMapping("/")
    public String get(Model model, Principal principal){
        model.addAttribute("fullName", userService.getPrincipalName(principal.getName()));
        model.addAttribute("allBags", donationService.sumAllBags());
        model.addAttribute("allDonation",donationService.sumAllDonation());
        return "app/index";
    }
}
