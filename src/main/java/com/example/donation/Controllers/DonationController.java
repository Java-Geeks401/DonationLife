package com.example.donation.Controllers;


import com.example.donation.Models.*;
import com.example.donation.Repositories.*;
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
    @Autowired
    CatalogItemRepository catalogItemRepository;

    @PostMapping ("/addDonation")//add as donator
    public RedirectView addDonation(String description, String status, int donationItemID, Principal principal){

        Donator donator=donorRepository.findByUsername(principal.getName());
        CatalogItem donationItem=catalogItemRepository.findById(donationItemID).get();
//        List<DonationReq> donationReq= requestDonateRepository.findAllByCatalogItem(donationItem);
        Donation donation= new Donation( description,status,donationItem,donator);
//        donation.getDonationsItems().getDonationReqList()
        donationRepository.save(donation);
     return new RedirectView("/pageOfDonation");
    }

    @GetMapping("/pageOfDonation")
    public String pageOfDonation(Model model, Principal principal) {
        Donator donator = donorRepository.findByUsername(principal.getName());
        List<Donation> donationList = donationRepository.findAllByDonatorItems(donator);
         Donation oneDonation =donationRepository.findById(1).get();
         CatalogItem numofitem =oneDonation.getDonationsItems();
//        donationReqList donationReqList =requestDonateRepository.findById(1).get();
       List<DonationReq> donationReq= numofitem.getDonationReqList();
        System.out.println(donationReq);
//          List<DonationReq> donationReqList =requestDonateRepository.findAllByCatalogItem(numofitem);
        model.addAttribute("donationList", donationList);
        model.addAttribute("donationReqList", donationReq);
        return "showDonation";
    }

    @GetMapping("/openform")
    public String openForm(){
        return "addReqDonation";
    }
    @GetMapping("/openformDonation")
    public String openformDonation(){
        return "addDonation";
    }

    // for req donation from charities
    @PostMapping("/RequestDonationForm")
    public RedirectView addingRequest(String description, boolean status, Principal principal,int number) {
        CharityOrganization charityOrganization=charityOrganizationRepositorie.findByUsername(principal.getName());
        CatalogItem donationItem=catalogItemRepository.findById(number).get();
        DonationReq requestDonate=new DonationReq(description,status,charityOrganization,donationItem);
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
