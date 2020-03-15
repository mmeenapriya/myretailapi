package com.my.retail.myretail.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigInteger;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = ProductDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
//    @Field(name="tcin")
String productId;
    String name;
    Double price;
    String error;
    String currency;

    public ProductDTO(String productId, String error ){
        this.productId = productId;
        this.name = error;
    }
    public ProductDTO(String error ){
        this.error = error;
    }
    public ProductDTO(String productId, String name, Double price ){
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}
