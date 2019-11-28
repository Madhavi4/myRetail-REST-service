package com.myretail.casestudy.repository;

import com.myretail.casestudy.model.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface ProductPriceRepository
 */
@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPrice, Integer> {

}
