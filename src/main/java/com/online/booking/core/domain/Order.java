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
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for order representation.
 */

@Document
public class Order extends AbstractDocument {
    @DBRef
    private Customer        customer;
    private Address         shippingAddress;
    private Set<OrderItem>  lineItems = new HashSet<OrderItem>();

    /**
     * Creates a new {@link Order} for the given {@link Customer}.
     *
     * @param customer must not be {@literal null}.
     * @param shippingAddress must not be {@literal null}.
     */
    @PersistenceConstructor
    public Order(Customer customer, Address shippingAddress) {

        Assert.notNull(customer, "Order customer must not be null");
        Assert.notNull(shippingAddress, "Shipping address must not be null");

        this.customer = customer;
        this.shippingAddress = shippingAddress;
    }

    /**
     * Adds the given {@link OrderItem} to the {@link Order}.
     *
     * @param orderItem
     */
    public void add(OrderItem orderItem) {
        this.lineItems.add(orderItem);
    }

    /**
     * Returns the {@link Customer} who placed the {@link Order}.
     *
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns all {@link OrderItem}s currently belonging to the {@link Order}.
     *
     * @return
     */
    public Set<OrderItem> getLineItems() {
        return Collections.unmodifiableSet(lineItems);
    }

    /**
     * Returns the total of the {@link Order}.
     *
     * @return
     */
    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : lineItems) {
            total = total.add(item.getTotal());
        }

        return total;
    }
}
