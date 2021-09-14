package com.online.booking.core.repository;

import com.online.booking.core.domain.ItemCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends MongoRepository<ItemCategory, String> {
}
