package com.online.booking.core.web.model;

import java.math.BigDecimal;

public class ItemList {

    private String      id;
    private String      title;
    private String      descriptionShort;
    private BigDecimal  price;
    private Rating      rating;

    public ItemList() {
    }

    public ItemList(String id, String title, String descriptionShort, BigDecimal price, Rating rating) {
        this.id = id;
        this.title = title;
        this.descriptionShort = descriptionShort;
        this.price = price;
        this.rating = rating;
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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
