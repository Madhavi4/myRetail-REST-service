package com.myretail.casestudy.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.myretail.casestudy.json.ProductApiResponseDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The pojo ProductApiResponse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = ProductApiResponseDeserializer.class)
public class ProductApiResponse {
    /**
     * The type Id
     */
    @NotNull(message = "product Id is null")
    private Long id;
    /**
     * The type name
     */
    @NotBlank(message = "Product name is blank")
    private String name;
}
