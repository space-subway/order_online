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

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import com.online.booking.core.domain.*;
import com.online.booking.core.repository.CustomerRepository;
import com.online.booking.core.repository.ItemRepository;
import com.online.booking.core.repository.OrderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.online.booking.AbstractIntegrationTest;


/**
 * Integration tests for {@link OrderRepository}.
 * 
 * @author
 */
public class OrderRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ItemRepository 	itemRepository;

	@Autowired
	CustomerRepository customerRepository;


	@Test
	public void createOrder() {

		Optional<Customer> user = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

		Item iPad = itemRepository.findAll().iterator().next();

		Order order = new Order(user.get(), user.get().getAddresses().iterator().next());
		order.add(new OrderItem(iPad));

		order = orderRepository.save(order);
		assertTrue( order.getId() != null );
	}

	@Test
	public void readOrder() {

		Optional<Customer> dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
		List<Order> orders = orderRepository.findByCustomer(dave.get());

		Order order = orders.iterator().next();
		OrderItem orderItem = order.getLineItems().stream().findFirst().orElse(null);

		assertNotNull(orderItem);

		Item iPad = itemRepository.findAll().iterator().next();

		assertEquals( iPad.getPrice(), orderItem.getTotal() );
	}
}
