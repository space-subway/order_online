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

package com.online.booking.core.document;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Class for any item representation.
 *
 * @author
 */
@Document
public class Item extends AbstractDocument {

    private String      tittle, description;
    private BigDecimal  price;

    /**
     * Creates a new {@link Item} with the given name.
     *
     * @param tittle must not be {@literal null} or empty.
     * @param price must not be {@literal null} or less than or equal to zero.
     */
    public Item(String tittle, BigDecimal price) {
        this(tittle, price, null);
    }

    /**
     * Creates a new {@link Item} from the given name and description.
     *
     * @param tittle must not be {@literal null} or empty.
     * @param price must not be {@literal null} or less than or equal to zero.
     * @param description
     */
    @PersistenceConstructor
    public Item(String tittle, BigDecimal price, String description) {

        Assert.hasText(tittle, "Name must not be null or empty!");
        Assert.isTrue(BigDecimal.ZERO.compareTo(price) < 0, "Price must be greater than zero!");

        this.tittle = tittle;
        this.price = price;
        this.description = description;
    }

    /**
     * Returns the {@link Item}'s tittle.
     *
     * @return
     */
    public String getTittle() {
        return tittle;
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
     * Returns the price of the {@link Item}.
     *
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }
}
