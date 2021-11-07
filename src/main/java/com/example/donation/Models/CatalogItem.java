package com.example.donation.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class CatalogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    @ManyToOne
    Catalog catalogItems;
    // relation with the donation
    @OneToMany(mappedBy = "donationsItems")
    private List<Donation> donationList;

    public CatalogItem(){
    }
    public CatalogItem(String itemName, Catalog catalogItems) {
        this.itemName = itemName;
        this.catalogItems = catalogItems;
    }

    public List<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(List<Donation> donationList) {
        this.donationList = donationList;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Catalog getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(Catalog catalogItems) {
        this.catalogItems = catalogItems;
    }
}
