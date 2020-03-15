package com.my.retail.myretail.service;

import com.my.retail.myretail.domain.Product;
import com.my.retail.myretail.pojo.ProductDTO;
import com.my.retail.myretail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductRepository repository;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public ProductDTO getProductInfo(String productId) throws Exception {
        ProductDTO productDTO = null;
            try {
                productDTO =  getProductDetails(productId);
                Product priceDetails = getProductPrice(productId);
                if(priceDetails!=null && productDTO!=null) {
                    productDTO.setPrice(priceDetails.getPrice());
                    productDTO.setCurrency(priceDetails.getCurrency());
                }else{
                    throw new IllegalArgumentException("Product Not Found");
                }
            }
            catch (HttpClientErrorException e) {
                if (HttpStatus.NOT_FOUND.equals(e.getStatusCode()) ){
                    throw new IllegalArgumentException("Product Not Found");
                }
                throw e;
            }
        return productDTO;
    }

    private ProductDTO getProductDetails(String productId) throws HttpClientErrorException, Exception {
        String url = "https://redsky.target.com/v2/pdp/tcin/" + productId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ProductDTO> response = null;
        if (restTemplate == null) {
            return null;
        } else {
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ProductDTO.class);
            }
            catch (HttpClientErrorException e) {
                throw e;
            }
        }
        return response == null ? null : response.getBody();
    }

    private Product getProductPrice(String productId) throws Exception {
        Product product = null;
            try {
                 product =  repository.findFirstByTcin(productId);
                System.out.println(product.getName()+" - "+product.getPrice());
            }
            catch (Exception e) {
                throw e;
            }
        return product;
    }
}

