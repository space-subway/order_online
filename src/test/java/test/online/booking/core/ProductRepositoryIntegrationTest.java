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

import static org.junit.Assert.*;
import java.math.BigDecimal;
import com.online.booking.core.document.Item;
import com.online.booking.core.repository.ItemRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.online.booking.AbstractIntegrationTest;

/**
 * Integration tests for {@link ItemRepository}.
 * 
 * @author
 */
public class ProductRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	ItemRepository itemRepository;

	@Test
	@SuppressWarnings("unchecked")
	public void createProduct() {

		Item product = new Item("Camera bag", new BigDecimal(49.99));
		product = itemRepository.save(product);

		assertNotNull( product );
	}


	@Test
	@SuppressWarnings("unchecked")
	public void findProductByTittle() {

		Item product = itemRepository.findByTittle("Dock");

		assertNotNull( product );
	}
}
