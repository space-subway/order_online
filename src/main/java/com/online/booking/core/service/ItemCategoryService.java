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
