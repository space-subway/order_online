package com.online.booking.core.web.model;

import com.online.booking.core.domain.ItemCategory;

public class ItemCategoryModel {
    private String id;
    private String name;

    public ItemCategoryModel() {
    }

    public ItemCategoryModel(String id, String name) {
        this.id     = id;
        this.name   = name;
    }

    public ItemCategoryModel(ItemCategory category) {
        this( category.getId(), category.getName() );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
