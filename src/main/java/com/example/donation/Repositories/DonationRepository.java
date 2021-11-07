package com.example.donation.Repositories;

import com.example.donation.Models.Donation;
import org.springframework.data.repository.CrudRepository;

public interface DonationRepository  extends CrudRepository<Donation,Integer> {

}
