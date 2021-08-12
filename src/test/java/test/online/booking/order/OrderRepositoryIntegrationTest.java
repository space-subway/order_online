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
package test.online.booking.order;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.online.booking.core.domain.*;
import com.online.booking.core.repository.ItemRepository;
import com.online.booking.core.repository.OrderRepository;
import com.online.booking.core.repository.CustomerRepository;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import test.online.booking.AbstractIntegrationTest;


/**
 * Integration tests for {@link OrderRepository}.
 *
 * @author
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderRepositoryIntegrationTest extends AbstractIntegrationTest {

	private static final String email = "dave@dmband.com";

	@Autowired
	OrderRepository 	orderRepository;

	@Autowired
	ItemRepository 		itemRepository;

	@Autowired
	CustomerRepository 	customerRepository;

	@Test
	public void Test1_createOrder() {

		Customer customer = customerRepository.findByEmailAddress(new EmailAddress(email));

		Item iPad = itemRepository.findAll().iterator().next();

		Order order = new Order(customer, customer.getAddresses().iterator().next());
		order.add(new OrderItem(iPad));

		order = orderRepository.save(order);

		assertNotNull( order );
		assertThat( order.getId(), is(notNullValue()) );
	}

	@Test
	public void Test2_readOrder() {

		Customer dave = customerRepository.findByEmailAddress(new EmailAddress(email));
		List<Order> orders = orderRepository.findByCustomer(dave);

		assertNotNull( orders );

		Order firstOrder = orders.iterator().next();

		Customer customer = firstOrder.getCustomer();
		assertThat(customer, Matchers.is(dave));

		Set<OrderItem> orderItems = firstOrder.getLineItems();
		assertNotNull(orderItems);
	}
}
