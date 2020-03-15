package com.my.retail.myretail.controller;

import com.my.retail.myretail.pojo.ProductDTO;
import com.my.retail.myretail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "/myretail/v1")
public class ProductInfoController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProductInfo(@PathVariable String productId) {

        try {
            Assert.notNull(productId);
            ProductDTO productDTOInfo = productService.getProductInfo(productId);
            System.out.println("productInfo - "+ productDTOInfo);

            System.out.println( "Name for product -  " + productDTOInfo.getProductId() + " is "+ productDTOInfo.getName());
            return new ResponseEntity<>(productDTOInfo, HttpStatus.OK);

        } catch(NumberFormatException e) {
            System.out.println("Please provide a valid product id");
            return new ResponseEntity<>(new ProductDTO(  "Product Id not valid"), HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            System.out.println(" Product not found ");
            return new ResponseEntity<>(new ProductDTO(  "Product Not Found"), HttpStatus.NOT_FOUND);
        }catch (Exception e ){
            System.out.println("Exception - "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

