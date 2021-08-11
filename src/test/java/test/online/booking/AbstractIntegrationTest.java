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

package test.online.booking;

import com.online.booking.core.domain.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBList;

import java.math.BigDecimal;

/**
 * Base class for integration tests adding some sample data through the MongoDB Java driver.
 * 
 * @author
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring-dispatecher-servlet.xml",
		"file:src/main/webapp/WEB-INF/spring-data-configuration.xml"
})
public abstract class AbstractIntegrationTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Before
	public void setUp() {

		// Customers
		mongoTemplate.dropCollection("user");

		Customer dave = new Customer("Dave", "Matthews");
		dave.setEmailAddress(new EmailAddress("dave@dmband.com"));
		Address address = new Address("Broadway","New York","United States");
		dave.add(address);
		mongoTemplate.insert( dave, "user" );

		// Products
		mongoTemplate.dropCollection("item");

		Item iPad 		= new Item("iPad", new BigDecimal(499.0));
		Item macBook 	= new Item( "MacBook Pro", new BigDecimal(1299.0) );
		Item dock		= new Item( "Dock", new BigDecimal(49.0) );

		mongoTemplate.insert(iPad, "item");
		mongoTemplate.insert(macBook, "item");
		mongoTemplate.insert(dock, "item");

		// Orders
		mongoTemplate.dropCollection("order");

		// Order items
		OrderItem iPadLineItem 		= new OrderItem( iPad, 2 );
		OrderItem macBookLineItem	= new OrderItem( macBook );

		mongoTemplate.insert( iPadLineItem, "orderItem" );
		mongoTemplate.insert( macBookLineItem, "orderItem" );

		BasicDBList lineItems = new BasicDBList();
		lineItems.add(iPadLineItem);
		lineItems.add(macBookLineItem);

		Order order = new Order( dave, address );

		mongoTemplate.insert(order, "order");
	}
}
