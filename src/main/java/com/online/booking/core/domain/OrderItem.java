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

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author
 */
@Document
public class OrderItem extends AbstractDocument {

    @DBRef
    private Item        product;
    private BigDecimal  price;
    private int         amount;

    /**
     * Creates a new {@link OrderItem} for the given {@link Item}.
     *
     * @param product must not be {@literal null}.
     */
    public OrderItem(Item product) {
        this(product, 1);
    }

    /**
     * Creates a new {@link OrderItem} for the given {@link Item} and amount.
     *
     * @param product must not be {@literal null}.
     * @param amount
     */
    public OrderItem(Item product, int amount) {

        Assert.notNull(product, "The given Product must not be null!");
        Assert.isTrue(amount > 0, "The amount of Products to be bought must be greater than 0!");

        this.product = product;
        this.amount = amount;
        this.price = product.getPrice();
    }

    public OrderItem() {
    }

    /**
     * Returns the {@link Item} the {@link OrderItem} refers to.
     *
     * @return
     */
    public Item getProduct() {
        return product;
    }

    /**
     * Returns the amount of {@link Item}s to be ordered.
     *
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the price a single unit of the {@link OrderItem}'s product.
     *
     * @return the price
     */
    public BigDecimal getUnitPrice() {
        return price;
    }

    /**
     * Returns the total for the {@link OrderItem}.
     *
     * @return
     */
    public BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(amount));
    }

}
