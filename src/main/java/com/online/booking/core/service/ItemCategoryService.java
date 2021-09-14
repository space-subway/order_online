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

import com.online.booking.core.domain.ItemCategory;
import com.online.booking.core.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryService {
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    public List<ItemCategory> readAll(){
        return itemCategoryRepository.findAll();
    }

    public ItemCategory save( ItemCategory itemCategory ){
        return itemCategoryRepository.save( itemCategory );
    }

    /**
     * remove item category by id
     *
     * @param id of item category
     * @return false if item category not found, true if item category deleted
     */
    public boolean remove( String id ){

        Optional<ItemCategory> itemCategory = itemCategoryRepository.findById( id );

        if( !itemCategory.isPresent() ) return false;

        itemCategoryRepository.delete( itemCategory.get() );

        return true;

    }
}
