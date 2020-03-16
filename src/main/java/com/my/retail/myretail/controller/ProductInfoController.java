package com.my.retail.myretail.controller;

import com.my.retail.myretail.domain.Product;
import com.my.retail.myretail.pojo.ProductDTO;
import com.my.retail.myretail.service.ProductService;
import com.my.retail.myretail.util.HelperUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/myretail/v1")
public class ProductInfoController {

    @Autowired
    ProductService productService;

    Logger logger = LoggerFactory.getLogger(ProductInfoController.class);

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductInfo(@PathVariable String id) {
        String error = null;
        try {
            error = HelperUtil.validate(id);
            if(!StringUtils.isEmpty(error)){
                return new ResponseEntity<>(new Product(  error), HttpStatus.BAD_REQUEST);
            }
            Product productDTOInfo = productService.getProductInfo(id);
            return new ResponseEntity<>(productDTOInfo, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            error = "Product Not Found";
            logger.error(" Product not found ");
            return new ResponseEntity<>(new Product(  error), HttpStatus.NOT_FOUND);
        }catch (Exception e ){
            logger.error("Exception retriving product details "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = {"/products/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProductPrice(@PathVariable String id, @RequestBody Product product) {
        String error = null;
        try {
            error = HelperUtil.validate(id);
            if (!StringUtils.isEmpty(error)) {
                return new ResponseEntity<>(new Product(error), HttpStatus.BAD_REQUEST);
            }
            Product productInfo = productService.updateProduct(product);
            return new ResponseEntity<>(productInfo, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            error = "Product Not Found";
            logger.error(" Product not found ");
            return new ResponseEntity<>(new Product(  error), HttpStatus.NOT_FOUND);
        }catch (Exception e ){
            logger.error("Exception retriving product details "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

