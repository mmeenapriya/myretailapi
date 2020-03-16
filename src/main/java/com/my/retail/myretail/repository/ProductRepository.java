package com.my.retail.myretail.repository;

import com.my.retail.myretail.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
//    Product findById(String productId);
}