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
import java.util.Optional;

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
    public Product getProductInfo(String productId) throws Exception {
        Product product = new Product();
        try {
                ProductDTO  productDTO =  getProductDetails(productId);
                if(productDTO == null ){
                    throw new IllegalArgumentException("Product Not Found ");
                }
                product.setProdDetails(productDTO.getProductId(), productDTO.getName());
                Optional<Product> priceDetails = getProductPrice(productId);
                priceDetails.ifPresent(it ->  product.setPriceDetails(it.getPrice(), it.getCurrency()));
            }
            catch (HttpClientErrorException e) {
                if (HttpStatus.NOT_FOUND.equals(e.getStatusCode()) ){
                    throw new IllegalArgumentException("Product Not Found");
                }
                throw e;
            }
        return product;
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

    private Optional<Product> getProductPrice(String productId) throws Exception {
        Optional<Product> product = Optional.empty();
            try {
                product =  repository.findById(productId);
//                 if(product1.isEmpty()){
//                     product   = updateProduct(product1.get());
//                 }
//                System.out.println(product.getName()+" - "+product.getPrice());
                return product;

            }
            catch (Exception e) {
                throw e;
            }
    }

    @Override
    public Product updateProduct(Product product) throws Exception{
        product.updateCurrency();
        return repository.save(product);
    }


}

