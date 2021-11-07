package com.example.donation.Models;

import javax.persistence.*;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    private String description;
    private String status;
    // Relation with the static catalog item.
    @ManyToOne
    CatalogItem donationsItems;
    //Relation with the donator.
    @ManyToOne
    Donator donatorItems;
    // Relation with the charity
    @ManyToOne
    CharityOrganization charityItems;
    public Donation() {
    }

    public Donation(String description, String status, CatalogItem donationsItems, Donator donatorItems) {
        this.description = description;
        this.status = status;
        this.donationsItems = donationsItems;
        this.donatorItems = donatorItems;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CatalogItem getDonationsItems() {
        return donationsItems;
    }

    public void setDonationsItems(CatalogItem donationsItems) {
        this.donationsItems = donationsItems;
    }

    public Donator getDonatorItems() {
        return donatorItems;
    }

    public void setDonatorItems(Donator donatorItems) {
        this.donatorItems = donatorItems;
    }

}