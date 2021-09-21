package com.online.booking.core.web.model;

import com.online.booking.core.domain.Item;

import java.math.BigDecimal;

public class ItemListModel {

    private String              id;
    private String              title;
    private String              descriptionShort;
    private BigDecimal          price;
    private ItemCategoryModel   category;


    public ItemListModel() {
    }

    public ItemListModel(String id, String title, String descriptionShort, BigDecimal price, ItemCategoryModel category) {
        this.id = id;
        this.title = title;
        this.descriptionShort = descriptionShort;
        this.price = price;
        this.category = category;

    }

    public ItemListModel(Item item) {
        this.id                 = item.getId();
        this.title              = item.getTitle();
        this.descriptionShort   = item.getDescriptionShort();
        this.price              = item.getPrice();
        this.category           = new ItemCategoryModel(item.getCategory());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ItemCategoryModel getCategory() {
        return category;
    }

    public void setCategory(ItemCategoryModel category) {
        this.category = category;
    }
}
