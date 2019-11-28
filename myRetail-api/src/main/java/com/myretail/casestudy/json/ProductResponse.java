package com.myretail.casestudy.json;

import com.myretail.casestudy.model.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * The entity class ProductResponse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    /**
     * The type product Id
     */
    private Integer id;
    /**
     * The type product name
     */
    @NotBlank(message = "Product name is mandatory")
    private String name;
    /**
     * The type product price
     */
    private ProductPrice current_price;
}
