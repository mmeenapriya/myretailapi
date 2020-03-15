package com.my.retail.myretail.pojo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigInteger;

public class ProductDeserializer extends JsonDeserializer<ProductDTO> {

    @Override
    public ProductDTO deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
//        BigInteger productId = BigInteger.ZERO;
        String productId = "";
        String name = null;
        JsonNode node = jp.getCodec().readTree(jp);
        if (node ==null ){
            throw new IllegalArgumentException("Product Not found");
        }
        if (node != null &&
                node.get("product") !=null) {
            if ( node.get("product").get("available_to_promise_network") != null &&
                    node.get("product").get("available_to_promise_network").get("product_id") != null
            ) {
//                productId = new BigInteger(node.get("product").get("available_to_promise_network").get("product_id").asText());
                productId = node.get("product").get("available_to_promise_network").get("product_id").asText();
            }

            if ( node.get("product").get("item") != null &&
                    node.get("product").get("item").get("product_description") != null &&
                    node.get("product").get("item").get("product_description").get("title") != null
            ) {
                name = node.get("product").get("item").get("product_description").get("title").textValue();
            }
        }
//        String itemName = node.get("itemName").asText();
//        int userId = (Integer) ((IntNode) node.get("createdBy")).numberValue();

        return new ProductDTO(productId,name);
    }
}