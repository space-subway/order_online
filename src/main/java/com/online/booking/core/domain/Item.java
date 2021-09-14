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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Class for any item representation.
 *
 */

@Document( "item" )
public class Item extends AbstractDocument {

    @NotNull(message = "is required")
    @NotEmpty
    private String      title;

    @NotNull(message = "is required")
    @NotEmpty
    private String      description;

    private String descriptionShort;

    @NotNull(message = "is required")
    @Min(value = 0, message = "price must be more than 0")
    private BigDecimal  price;

    @Min(0)
    private Integer     viewCount;
    private Rating      rating;

    @DBRef
    private ItemCategory category;

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
     * Creates a new {@link Item} with the given name.
     *
     * @param title must not be {@literal null} or empty.
     * @param price must not be {@literal null} or less than or equal to zero.
     * @param category
     */
    public Item(String title, BigDecimal price, ItemCategory category) {
        this(title, price, null, null);
        this.category = category;
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
        this.viewCount = 0;
        this.rating = new Rating( 0, 0, 0, 0, 0 );
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
    public Rating getRating() { return rating; }

    public void setRating(
            Integer oneStarCount,
            Integer twoStarCount,
            Integer threeStarCount,
            Integer fourStarCount,
            Integer fiveStarCount
    ) {
        Rating rating = new Rating( oneStarCount,
                                    twoStarCount,
                                    threeStarCount,
                                    fourStarCount,
                                    fiveStarCount );

        this.rating = rating;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }
}
