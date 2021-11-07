package com.example.donation.Models;

import javax.persistence.*;


@Entity
public class DonationReq {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String description;
        private boolean status;
        @ManyToOne
        private CharityOrganization charityOrganization;

public DonationReq(){

}

    public DonationReq(String discription, boolean status, CharityOrganization charityOrganization) {
            this.description = discription;
            this.status = status;
            this.charityOrganization=charityOrganization;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDiscription() {
            return description;
        }

        public void setDiscription(String discription) {
            this.description = discription;
        }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CharityOrganization getCharityOrganization() {
        return charityOrganization;
    }

    public void setCharityOrganization(CharityOrganization charityOrganization) {
        this.charityOrganization = charityOrganization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

