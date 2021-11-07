package com.example.donation.Controllers;


import com.example.donation.Models.CharityOrganization;
import com.example.donation.Repositories.CharityOrganizationRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CharityOrganizationController {
    @Autowired
    CharityOrganizationRepositorie charityOrganizationRepositorie;

    @GetMapping("/allCharityOrganization")
    public String getAllCharityOrganization(Model model){
        model.addAttribute("charityOrganization" ,charityOrganizationRepositorie.findAll());
        return "allCharityOrganization";
    }
    @GetMapping("/charityOrganizationForm")
    public String getCharityOrganizationForm(Model model){
        model.addAttribute("CharityOrganization",new CharityOrganization());
        return "CharityOrganization";
    }
    @PostMapping("/charityOrganizationForm")
    public RedirectView addcharityOrganizationForm(@ModelAttribute CharityOrganization charityOrganization, Model model) {

        charityOrganizationRepositorie.save(charityOrganization);
        model.addAttribute("CharityOrganization", charityOrganization);
        return  "allCharityOrganization";

    }
}
