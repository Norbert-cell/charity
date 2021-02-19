package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public AdminController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionService.findAll();
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

}
