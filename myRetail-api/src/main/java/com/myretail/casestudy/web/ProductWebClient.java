package com.myretail.casestudy.web;

import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.exceptions.ProductServiceException;
import com.myretail.casestudy.model.ProductApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

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

    public ProductApiResponse retrieveProductName(Long productId) throws ProductServiceException {
        try {
            String productUrl = StringUtils.replace(redskyUrl, PRODUCT_ID_PARAM, productId.toString());
            ResponseEntity<ProductApiResponse> response = redskyRestTemplate.getForEntity(productUrl, ProductApiResponse.class);
            log.info("product details request url: {} statusCode: {}", productUrl, response.getStatusCode());
            log.debug("Redsky Response: {}", response.getBody());
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFoundException("Product not available for productId: " + productId, e);
            }
            throw new ProductServiceException("failed retrieving product", e);
        } catch (Exception e) {
            throw new ProductServiceException("failed retrieving product", e);
        }
    }

}
