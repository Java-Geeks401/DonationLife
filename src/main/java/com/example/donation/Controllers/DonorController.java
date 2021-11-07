package com.example.donation.Controllers;

import com.example.donation.Models.Admin;
import com.example.donation.Models.Donator;
import com.example.donation.Repositories.AdminRepository;
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
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class DonorController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DonorRepository donorRepository;
    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/profile")
    public String getMyProfilePage(Model m,Principal principal){
        try {
            Donator donator = donorRepository.findByUsername(principal.getName());
//            Admin admin= adminRepository.findByUsername(principal.getName());
            m.addAttribute("donator", donator);
//            m.addAttribute("adminData", admin);
            return "profile";
        }
        catch(Exception e){
            return "profile";
        }
    }
    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView postDonate( String username, String password, String firstName,
                                   String lastName,String dateOfBirth){
        Donator donator=new Donator(username,passwordEncoder.encode(password),firstName,lastName,dateOfBirth);
        donator=donorRepository.save(donator);
        Authentication authentication = new UsernamePasswordAuthenticationToken(donator, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
    }
}
