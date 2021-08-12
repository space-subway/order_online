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

package com.online.booking.core.service;

import com.online.booking.core.domain.Customer;
import com.online.booking.core.domain.EmailAddress;
import com.online.booking.core.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business logic for {@link Customer}s.
 *
 */

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void create( Customer user ) throws DuplicateKeyException, NullPointerException {

        if( user == null ) throw new NullPointerException("User must not be null!");

        if( readByEmailAddress( user.getEmailAddress() ) != null ) throw new DuplicateKeyException("User with email: " + user.getEmailAddress() + " already exist!");

        repository.save( user );
    }

    public Customer readByEmailAddress( EmailAddress email ){
        return repository.findByEmailAddress( email );
    }

    public List<Customer> readAll(){
        return repository.findAll();
    }

}
