package com.myretail.casestudy.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The pojo ProductDetails
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
    /**
     * The type Id
     */
    @NotNull(message = "product Id is null")
    private Integer id;
    /**
     * The type name
     */
    @NotBlank(message = "Product name is blank")
    private String name;
}
