package com.myretail.casestudy.json;

import com.myretail.casestudy.model.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private Long id;
    /**
     * The type product name
     */
    @NotBlank(message = "Product name is mandatory")
    private String name;
    /**
     * The type product price
     */
    @NotNull(message = "Product price is mandatory")
    private ProductPrice current_price;
}
