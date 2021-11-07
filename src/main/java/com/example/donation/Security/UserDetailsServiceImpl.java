package com.example.donation.Security;

import com.example.donation.Repositories.CharityOrganizationRepositorie;
import com.example.donation.Repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    CharityOrganizationRepositorie charityOrganizationRepositorie;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return donorRepository.findByUsername(username);
    }

}
