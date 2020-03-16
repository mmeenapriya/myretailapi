package com.my.retail.myretail.controller;

import com.my.retail.myretail.domain.Product;
import com.my.retail.myretail.pojo.ProductDTO;
import com.my.retail.myretail.service.ProductService;
import com.my.retail.myretail.util.HelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "/myretail/v1")
public class ProductInfoController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductInfo(@PathVariable String id) {
        String error = null;
        try {
            error = HelperUtil.validate(id);
            if(!StringUtils.isEmpty(error)){
                return new ResponseEntity<>(new Product(  error), HttpStatus.BAD_REQUEST);
            }
            Product productDTOInfo = productService.getProductInfo(id);
            System.out.println("productInfo - "+ productDTOInfo);

            System.out.println( "Name for product -  " + productDTOInfo.getId() + " is "+ productDTOInfo.getName());
            return new ResponseEntity<>(productDTOInfo, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            error = "Product Not Found";
            System.out.println(" Product not found ");
            return new ResponseEntity<>(new Product(  error), HttpStatus.NOT_FOUND);
        }catch (Exception e ){
            System.out.println("Exception - "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = {"/products/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProductPrice(@PathVariable String id, @RequestBody Product product) {

        try {
            Product productInfo = productService.updateProduct(product);
            System.out.println("productInfo - "+ productInfo);

            System.out.println( "Name for product -  " + productInfo.getName() + " is "+ productInfo.getPrice());
            return new ResponseEntity<>(productInfo, HttpStatus.OK);

        } catch(NumberFormatException e) {
            System.out.println("Please provide a valid product id");
            return new ResponseEntity<>(new Product(  "Product Id not valid"), HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            System.out.println(" Product not found ");
            return new ResponseEntity<>(new Product(  "Product Not Found"), HttpStatus.NOT_FOUND);
        }catch (Exception e ){
            System.out.println("Exception - "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

