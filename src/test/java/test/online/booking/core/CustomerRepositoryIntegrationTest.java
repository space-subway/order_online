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
package test.online.booking.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.online.booking.core.domain.Address;
import com.online.booking.core.domain.Customer;
import com.online.booking.core.domain.EmailAddress;
import com.online.booking.core.repository.CustomerRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import test.online.booking.AbstractIntegrationTest;

/**
 * Integration tests for {@link CustomerRepository}.
 * 
 * @author
 */
public class CustomerRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void customerReadWriteTest() {
		EmailAddress email = new EmailAddress("alicia@keys.com");

		Customer alicia = new Customer("Alicia", "Keys");
		alicia.setEmailAddress(email);
		alicia.add(new Address("27 Broadway", "New York", "United States"));

		Customer result = customerRepository.save(alicia);
		assertThat(result.getId(), is(notNullValue()));
	}

	@Test
	public void readsCustomerByEmail() {

		EmailAddress email = new EmailAddress("alicia@keys.com");
		Customer alicia = new Customer("Alicia", "Keys");
		alicia.setEmailAddress(email);

		customerRepository.save(alicia);

		Customer result = customerRepository.findByEmailAddress(email);
		assertThat(result, is(alicia));
	}

	@Test(expected = DuplicateKeyException.class)
	public void preventsDuplicateEmail() {

		Customer dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

		Customer anotherDave = new Customer("Dave", "Matthews");
		anotherDave.setEmailAddress(dave.getEmailAddress());

		customerRepository.save(anotherDave);
	}
}
