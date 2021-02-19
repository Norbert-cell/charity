package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validator.EditUserValidationGroup;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public AdminController(InstitutionService institutionService, DonationService donationService, UserService userService, PasswordEncoder passwordEncoder) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionService.findAll();
    }

    @ModelAttribute("adminName")
    public String adminName(Principal principal){
        return userService.getPrincipalName(principal.getName());
    }

    @GetMapping("/")
    public String get(){
    return "admin/index";
    }

    @GetMapping("/charity")
    public String charity(){
    return "admin/charityList";
    }

    @GetMapping("/charity/add")
    public String addInstitution(Model model){
        model.addAttribute("institution", new Institution());
    return "admin/addCharity";
    }

    @PostMapping("/charity/add")
    public String addInstitution(@ModelAttribute @Valid Institution institution, BindingResult result){
        if (result.hasErrors()){
            return "admin/addCharity";
        }
        institutionService.save(institution);
    return "redirect:/admin/charity";
    }


    @GetMapping("/charity/details/{institutionId}")
    public String detailsCharity(@PathVariable long institutionId, Model model){
        model.addAttribute("donation",donationService.sumAllDonationForInputInstitution(institutionId));
        model.addAttribute("institution", institutionService.findById(institutionId));
    return "admin/charityDetails";
    }

    @GetMapping("/charity/edit/{institutionId}")
    public String editCharity(@PathVariable long institutionId, Model model){
        model.addAttribute("institution", institutionService.findById(institutionId));
    return "admin/editCharity";
    }

    @PostMapping("/charity/edit/{institutionId}")
    public String editCharity(@ModelAttribute @Valid Institution institution, BindingResult result){
        if (result.hasErrors()){
            return "admin/editCharity";
        }
        institutionService.save(institution);
        return "redirect:/admin/charity";
    }

    @GetMapping("/charity/remove/{institutionId}")
    public String removeCharity(@PathVariable long institutionId){
        institutionService.remove(institutionId);
        return "redirect:/admin/charity";
    }

    @GetMapping("/list/admin")
    public String adminList(Model model){
        model.addAttribute("users", userService.findAllWhereRole(Role.ROLE_ADMIN));
        return "admin/adminList";
    }

    @GetMapping("/list/user")
    public String userList(Model model){
        model.addAttribute("users", userService.findAllWhereRole(Role.ROLE_USER));
    return "admin/userList";
    }

    @GetMapping("/registry")
    public String registryAdmin(Model model){
        model.addAttribute("user", new User());
    return "admin/registryAdmin";
    }

    @PostMapping("/registry")
    public String registry(@ModelAttribute @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "admin/registryAdmin";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        userService.saveUser(user);
    return "redirect:/admin/";
    }

    @GetMapping("/details/user/{userId}")
    public String userDetails(@PathVariable long userId, Model model){
        model.addAttribute("user",userService.findById(userId));
    return "admin/userDetails";
    }

    @GetMapping("/details/admin/{userId}")
    public String adminDetails(@PathVariable long userId, Model model){
        model.addAttribute("user",userService.findById(userId));
        return "admin/userDetails";
    }

    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable long userId, Model model){
        model.addAttribute("user", userService.findById(userId));
    return "admin/editUser";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute @Validated(EditUserValidationGroup.class) User user, BindingResult result){
        if (result.hasErrors()){
            return "admin/editUser";
        }
        userService.saveUser(user);
    return "redirect:/admin/";
    }

    @GetMapping("/remove/{userId}")
    public String removeUser(@PathVariable long userId){
        userService.remove(userId);
    return "redirect:/admin/";
    }

    @GetMapping("/block/{userId}")
    public String blockUser(@PathVariable long userId){
        userService.blockUser(userId);
    return "redirect:/admin/details/user/"+userId;
    }
}
