package com.myretail.casestudy.web;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.json.ProductDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 * The class ProductWebClient
 */
@Component
@Slf4j
public class ProductWebClient {

    private static final String PRODUCT_ID_PARAM = "{product_id}";
    @Autowired
    private RestTemplate redskyRestTemplate;
    @Value("${redsky.product.url}")
    private String redskyUrl;

    public ProductDetails retrieveProductName(Long productId) {

        try {
            log.info("In retrieveProductName(): ");
            String productUrl = StringUtils.replace(redskyUrl, PRODUCT_ID_PARAM, productId.toString());
            log.debug("redsky product url: {}", productUrl);

            ResponseEntity<String> response = redskyRestTemplate.getForEntity(productUrl, String.class);
            log.info("Status code: {}", response.getStatusCode());
            log.debug("Redsky Response: {}", response.getBody());

            Long id = Long.parseLong(JsonPath.parse(response.getBody()).read("$.product.item.tcin"));
            String name = JsonPath.parse(response.getBody()).read("$.product.item.product_description.title");
            return new ProductDetails(id, name);

        } catch (RestClientException e) {
            throw new ProductNotFoundException("Product not available at redsky.target.com", e);
        } catch (PathNotFoundException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
