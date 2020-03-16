package com.my.retail.myretail.service;

import com.my.retail.myretail.domain.Product;
import com.my.retail.myretail.pojo.ProductDTO;
import com.my.retail.myretail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

public interface ProductService {
    Product getProductInfo(String productId) throws Exception;
    Product updateProduct(Product productId) throws Exception;
}
