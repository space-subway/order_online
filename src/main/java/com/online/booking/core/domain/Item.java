/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.online.booking.core.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Class for any item representation.
 *
 */

@Document
public class Item extends AbstractDocument {

    @NotNull(message = "is required")
    private String      title;
    private String      description, descriptionShort;
    @NotNull(message = "is required")
    @Min(value = 1l, message = "must be more than 1")
    private BigDecimal  price;
    @Min(0)
    private Integer     viewCount;
    @Min(0l)
    @Max(5l)
    private Float       rating;

    /**
     * Creates a new {@link Item} with the given name.
     *
     * @param tittle must not be {@literal null} or empty.
     * @param price must not be {@literal null} or less than or equal to zero.
     */
    public Item(String tittle, BigDecimal price) {
        this(tittle, price, null, null);
    }

    /**
     * Creates a new {@link Item} from the given name and description.
     *
     * @param title must not be {@literal null} or empty.
     * @param price must not be {@literal null} or less than or equal to zero.
     * @param description
     */
    @PersistenceConstructor
    public Item(String title, BigDecimal price, String description, String descriptionShort) {

        Assert.hasText(title, "Name must not be null or empty!");
        Assert.isTrue(BigDecimal.ZERO.compareTo(price) < 0, "Price must be greater than zero!");

        this.title = title;
        this.price = price;
        this.description = description;
        this.descriptionShort = descriptionShort;
    }

    /**
     * Returns the {@link Item}'s tittle.
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the {@link Item}'s description.
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the {@link Item}'s short description.
     *
     * @return
     */
    public String getDescriptionShort() { return descriptionShort; }

    /**
     * Returns the price of the {@link Item}.
     *
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Returns count of view for the {@link Item}.
     *
     * @return
     */
    public Integer getViewCount() { return viewCount; }

    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }

    /**
     * Returns the rating of the {@link Item}.
     *
     * @return
     */
    public Float getRating() { return rating; }

    public void setRating(Float rating) { this.rating = rating; }
}
