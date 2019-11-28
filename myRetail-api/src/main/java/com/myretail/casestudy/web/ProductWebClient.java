package com.myretail.casestudy.web;

import com.jayway.jsonpath.JsonPath;
import com.myretail.casestudy.json.ProductDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

/**
 * The class ProductWebClient
 */
@Component
@Slf4j
public class ProductWebClient {

    private static final String PRODUCT_ID_PARAM = "{product_id}";
    @Autowired
    private RestTemplate restTemplate;
    @Value("${redsky.product.url}")
    private String redskyUrl;

    public ProductDetails retrieveProductName(Integer productId) {

        try {
            log.info("In retrieveProductName(): ");
            String productUrl = StringUtils.replace(redskyUrl, PRODUCT_ID_PARAM, productId.toString());
            log.debug("redsky product url: {}", productUrl);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(productUrl, HttpMethod.GET, entity, String.class);

            log.info("Status code: {}", response.getStatusCode());
            log.debug("Response body: {}", response.getBody());


            if (Objects.isNull(response.getBody())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Integer id = Integer.parseInt(JsonPath.parse(response.getBody()).read("$.product.item.tcin"));
            String name = JsonPath.parse(response.getBody()).read("$.product.item.product_description.title");
            return new ProductDetails(id, name);
        } catch (RestClientException e) {
            log.error("Error while retrieving the redsky json: {}", e.getMessage(), e.getStackTrace());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
