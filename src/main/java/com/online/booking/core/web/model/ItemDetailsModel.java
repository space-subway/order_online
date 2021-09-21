package com.online.booking.core.web.model;

import com.online.booking.core.domain.Item;

public class ItemDetailsModel {

    private String description;

    public ItemDetailsModel() {
    }

    public ItemDetailsModel( Item item ) {
        this.description = item.getDescription();
    }

    public ItemDetailsModel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
