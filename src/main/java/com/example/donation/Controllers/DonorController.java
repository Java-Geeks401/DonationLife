package com.example.donation.Controllers;

import com.example.donation.Models.Donator;
import com.example.donation.Repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class DonorController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DonorRepository donorRepository;

//    @GetMapping("/signup")
//    public String getDonators(Model model, Principal principal){
//        Donator donators=donorRepository.findByUsername(principal.getName());
//        model.addAttribute("donator",donators);
//        return "signup";
//
//    }


    @GetMapping("/profile")
    public String getMyProfilePage(){
        return "myprofile";
    }
    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup/donator")
    public RedirectView postDonate(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String firstName,
                                   @RequestParam String lastName, @RequestParam String dateOfBirth){

        Donator donator=new Donator(username,passwordEncoder.encode(password),firstName,lastName,dateOfBirth);
        Authentication authentication = new UsernamePasswordAuthenticationToken(donator, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        donorRepository.save(donator);

        return new RedirectView("/profile");

    }

}
