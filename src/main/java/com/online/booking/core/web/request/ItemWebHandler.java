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

package com.online.booking.core.web.request;

import com.online.booking.core.domain.Item;
import com.online.booking.core.repository.ItemRepository;
import com.online.booking.core.service.ItemService;
import com.online.booking.core.web.request.exception.ItemCreationException;
import com.online.booking.core.web.request.exception.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ItemWebHandler {

    @Autowired
    private ItemService itemService;

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
    public ResponseEntity<Item> create(@RequestBody Item item) throws ItemCreationException {

        Item createdItem = itemService.create(item);

        if( createdItem == null ) throw new ItemCreationException( Item.class.getName() );

        return new ResponseEntity<>( createdItem, HttpStatus.CREATED );
    }

    /**
     * Delete item by id
     *
     * @return Code of operation
     */
    /*@RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseMessage delete(
            @PathVariable( value = "id" ) String id
    ){
        Optional<Item> o = itemRepository.findById( id );

        ResponseMessage message = new ResponseMessage();
        if( !o.isPresent() ) {
            message.setMessage( "error" );
            return message;
        }

        itemRepository.delete(o.get());

        message.setMessage( "ok" );
        return message;
    }*/
}