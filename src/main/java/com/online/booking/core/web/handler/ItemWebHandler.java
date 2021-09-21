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

package com.online.booking.core.web.handler;

import com.online.booking.core.domain.Item;
import com.online.booking.core.domain.ItemCategory;
import com.online.booking.core.service.ItemCategoryService;
import com.online.booking.core.service.ItemService;
import com.online.booking.core.utils.BindingError;
import com.online.booking.core.web.exception.ItemCreationException;
import com.online.booking.core.web.exception.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemWebHandler {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * Return list of all available items
     *
     * @return Item list
     */
    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Item> list(){
        return itemService.readAll();
    }

    /**
     * Return item details by id
     *
     * @return Item
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Item> details(
            @PathVariable( value = "id" ) String id
    ) throws UnknownIdentifierException {
        Optional<Item> o = itemService.readById( id );

        if( o.isPresent() ) {
            //increment view count
            Item item = itemService.incViewCount( o.get() );

            return new ResponseEntity<>(o.get(), HttpStatus.OK );
        }

        throw new UnknownIdentifierException( id );
    }

    /**
     * Create new item and save it
     *
     * @return Code of operation
     */
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(
            @Valid @RequestBody Item item,
            BindingResult bindingResult
    ) throws ItemCreationException {

        if( bindingResult.hasErrors() ){

            List<FieldError> errors = bindingResult.getFieldErrors();

            List<BindingError> validFormErrList = new ArrayList<>(errors.size());

            errors.forEach( e ->
                    validFormErrList.add( new BindingError( e.getField(), e.getDefaultMessage() ) )
            );

            return new ResponseEntity<>( validFormErrList, HttpStatus.BAD_REQUEST );

        } else {

            if( item.getCategory() == null ) throw new ItemCreationException( Item.class.getName() );

            Item createdItem = itemService.create( item );

            if( createdItem == null ) throw new ItemCreationException( Item.class.getName() );

            return new ResponseEntity<>( createdItem, HttpStatus.CREATED );
        }
    }

    /**
     * Remove item by id
     *
     * @return id of deleted item
     */
    @RequestMapping(
            value = "/remove/{id}",
            method = RequestMethod.DELETE
    )
    public @ResponseBody String deleteItem(
            @PathVariable( value = "id" ) String id
    ) throws UnknownIdentifierException {

        if( !itemService.remove( id ) ) throw new UnknownIdentifierException( id );

        return id;
    }

    /**
     * Create new item category and save it
     *
     * @return Code of operation and new item category
     */
    @RequestMapping(
            value = "/create/category",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ItemCategory> createItemCategory(
            @RequestBody ItemCategory itemCategory
    ){
        ItemCategory category = itemCategoryService.save( itemCategory );

        if( category == null ) return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Remove item category by id
     *
     * @return id of deleted item category
     */
    @RequestMapping(
            value = "/category/remove/{id}",
            method = RequestMethod.DELETE
    )
    public @ResponseBody String deleteItemCategory(
            @PathVariable( value = "id" ) String id
    ) throws UnknownIdentifierException {
        if( !itemCategoryService.remove( id ) ) throw new UnknownIdentifierException( id );

        return id;
    }

}