package com.myretail.casestudy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The entity class ProductDetails
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails extends RepresentationModel<ProductDetails> {
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
    @Valid
    @NotNull(message = "Product price is mandatory")
    private ProductPrice current_price;
}
