package com.example.donation.Models;

import javax.persistence.*;

@Entity
public class CatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    @ManyToOne
    Catalog catalogItems;

    public CatalogItem(String itemName, Catalog catalogItems) {
        this.itemName = itemName;
        this.catalogItems = catalogItems;
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
