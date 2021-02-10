package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionService.findAll();
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("allBags", donationService.sumAllBags());
        model.addAttribute("allDonation",donationService.sumAllDonation());
        return "index";
    }
}
