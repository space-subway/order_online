package com.online.booking.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document( collection = "category")
public class ItemCategory extends AbstractDocument {
    @NotNull
    private String name;

    public ItemCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
