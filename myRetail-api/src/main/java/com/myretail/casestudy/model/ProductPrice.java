package com.myretail.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The entity class ProductPrice
 */
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {
    /**
     * The product id unique key
     */
    @Id
    @JsonIgnore
    private Long id;
    @NotNull(message = "currency_value is mandatory")
    private Double value;
    @NotEmpty(message = "currency_code is mandatory")
    private String currency_code;
}
