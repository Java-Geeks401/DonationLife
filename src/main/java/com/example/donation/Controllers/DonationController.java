package com.example.donation.Controllers;


import com.example.donation.Models.*;
import com.example.donation.Repositories.CharityOrganizationRepositorie;
import com.example.donation.Repositories.DonationRepository;
import com.example.donation.Repositories.DonorRepository;
import com.example.donation.Repositories.RequestDonateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class DonationController {
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    CharityOrganizationRepositorie charityOrganizationRepositorie;
    @Autowired
    RequestDonateRepository requestDonateRepository;

    @GetMapping("/addDonation")
    public RedirectView addDonation(String description, String status, CatalogItem donationsItems, Principal principal){

        Donator donator=donorRepository.findByUsername(principal.getName());
     Donation donation= new Donation( description,  status,donationsItems,donator);
     donationRepository.save(donation);
     return new RedirectView("/");
    }

    @GetMapping("/openform")
    public String openForm(){
        return "addReqDonation";
    }

    // for req donation from charities
    @PostMapping("/RequestDonationForm")
    public RedirectView addingRequest(String description, boolean status, Principal principal) {
        CharityOrganization charityOrganization=charityOrganizationRepositorie.findByUsername(principal.getName());
        DonationReq requestDonate=new DonationReq( description,status,charityOrganization);
        requestDonateRepository.save(requestDonate);

        return new RedirectView("/charityRequests");
    }


    @GetMapping("/charityRequests")
    public String charityRequests(Model model, Principal principal) {
        CharityOrganization charityOrganization = charityOrganizationRepositorie.findByUsername(principal.getName());
        List<DonationReq> requestDonates = requestDonateRepository.findAllByCharityOrganization(charityOrganization);
        model.addAttribute("requestDonates", requestDonates);
        return "showReq";
    }
}
