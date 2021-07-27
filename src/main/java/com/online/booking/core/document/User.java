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

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for users store and representation.
 *
 * @author
 */
@Document
public class User extends AbstractDocument {

    private String firstname, lastname;

    @Field("email")
    @Indexed(unique = true)
    private EmailAddress emailAddress;
    private Set<Address> addresses = new HashSet<Address>();

    /**
     * Creates a new {@link User} from the given firstname and lastname.
     *
     * @param firstname must not be {@literal null} or empty.
     * @param lastname must not be {@literal null} or empty.
     */
    public User(String firstname, String lastname) {

        Assert.hasText(firstname, "First name must not be null or empty!");
        Assert.hasText(lastname, "Last name must not be null or empty!");

        this.firstname = firstname;
        this.lastname = lastname;
    }

    protected User() {

    }

    /**
     * Adds the given {@link Address} to the {@link User}.
     *
     * @param address must not be {@literal null}.
     */
    public void add(Address address) {

        Assert.notNull(address);
        this.addresses.add(address);
    }

    /**
     * Returns the firstname of the {@link User}.
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Returns the lastname of the {@link User}.
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the lastname of the {@link User}.
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Returns the {@link EmailAddress} of the {@link User}.
     *
     * @return
     */
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the {@link User}'s {@link EmailAddress}.
     *
     * @param emailAddress must not be {@literal null}.
     */
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Return the {@link User}'s addresses.
     *
     * @return
     */
    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }
}
