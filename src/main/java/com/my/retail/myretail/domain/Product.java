package com.my.retail.myretail.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.my.retail.myretail.pojo.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of= {"id"})
@Document(collection = "productprice")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private static final String DEFAULT_CURRENCY = "USD";

    @Id
    @Field("_id")
    private String id;
    private String name;
    private Double price;
    private String currency;
    String error;

    public Product(ProductDTO productDTO){
        this.id = productDTO.getProductId();
        this.price = productDTO.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Product(String error){
        this.error = error;
    }

    public void setProdDetails(String  id, String name){
        this.id = id;
        this.name = name;
    }

    public void updateCurrency(){
        if (StringUtils.isEmpty(this.getCurrency())){
            this.setCurrency(DEFAULT_CURRENCY);
        }

    }

    public void setPriceDetails(Double price, String currency){
        this.price = price;
        if (StringUtils.isEmpty(currency)){
            currency = DEFAULT_CURRENCY;
        }
        this.currency = currency;
    }

}
