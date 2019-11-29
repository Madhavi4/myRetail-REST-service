package com.myretail.casestudy.controller;

import com.myretail.casestudy.json.ProductResponse;
import com.myretail.casestudy.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The Products controller class
 */
@RestController
@RequestMapping("/v1")
@Tag(name = "product", description = "The Product API")
public class ProductController {
    /**
     * The Product Service
     */
    @Autowired
    private ProductService productService;

    /**
     * Method to get Get Product details by id
     * @param id productId
     * @return product details
     */
    @Operation(summary = "Get Product details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    @GetMapping(path = "/products/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> retrieveProductDetails(@Parameter(description = "Id of the Product to be retrieved. Cannot be empty.",
            required = true) @PathVariable Long id) {

        ProductResponse productResponse = productService.getProductDetails(id);

        return ResponseEntity.ok(productResponse);
    }

    /**
     * Method to update Product details by id
     * @param id      productId
     * @param request product details request
     * @return updated product details
     */
    @Operation(summary = "Update product price by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping(path = "/products/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> updateProductDetailsById(@Parameter(description = "Id of the Product to be updated. Cannot be empty.",
            required = true) @PathVariable Long id, @Valid @RequestBody ProductResponse request) {

        return new ResponseEntity<>(productService.updateProductDetails(request), HttpStatus.CREATED);
    }
}
