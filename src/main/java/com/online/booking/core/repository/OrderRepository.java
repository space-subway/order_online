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

package com.online.booking.core.repository;

import com.online.booking.core.domain.Customer;
import com.online.booking.core.domain.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to access {@link Order}s.
 *
 */

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {

    /**
     * Returns all {@link Order}s of the given {@link Customer}.
     *
     * @param customer
     * @return
     */
    List<Order> findByCustomer(Customer customer);
}
