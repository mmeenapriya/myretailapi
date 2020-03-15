package com.my.retail.myretail.service;

import com.my.retail.myretail.pojo.ProductDTO;
import com.my.retail.myretail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

public interface ProductService {
    ProductDTO getProductInfo(String productId) throws Exception;
}
