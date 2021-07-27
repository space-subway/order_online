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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import com.online.booking.core.document.Address;
import com.online.booking.core.document.EmailAddress;
import com.online.booking.core.document.User;
import com.online.booking.core.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import test.online.booking.AbstractIntegrationTest;

/**
 * Integration tests for {@link UserRepository}.
 * 
 * @author
 */
public class CustomerRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void savesCustomerCorrectly() {

		EmailAddress email = new EmailAddress("alicia@keys.com");

		User dave = new User("Alicia", "Keys");
		dave.setEmailAddress(email);
		dave.add(new Address("27 Broadway", "New York", "United States"));

		User result = userRepository.save(dave);
		assertThat(result.getId(), is(notNullValue()));
	}

	@Test
	public void readsCustomerByEmail() {

		EmailAddress email = new EmailAddress("alicia@keys.com");
		User alicia = new User("Alicia", "Keys");
		alicia.setEmailAddress(email);

		userRepository.save(alicia);

		User result = userRepository.findByEmailAddress(email);
		assertThat(result, is(alicia));
	}

	@Test(expected = DuplicateKeyException.class)
	public void preventsDuplicateEmail() {

		User dave = userRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

		User anotherDave = new User("Dave", "Matthews");
		anotherDave.setEmailAddress(dave.getEmailAddress());

		userRepository.save(anotherDave);
	}
}
