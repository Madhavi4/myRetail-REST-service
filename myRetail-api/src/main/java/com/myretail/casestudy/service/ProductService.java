package com.myretail.casestudy.service;

import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.json.ProductDetails;
import com.myretail.casestudy.json.ProductResponse;
import com.myretail.casestudy.model.ProductPrice;
import com.myretail.casestudy.repository.ProductPriceRepository;
import com.myretail.casestudy.web.ProductWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The class ProductService
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductPriceRepository productPriceRepo;
    @Autowired
    private ProductWebClient productWebClient;

    /**
     * @param id productId
     * @return product details
     */
    public ProductResponse getProductDetails(Long id) {
        log.info("In getProductDetails()");
        ProductDetails productDetails = productWebClient.retrieveProductName(id);

        log.info("retrieving product price from db");
        Optional<ProductPrice> optProductPrice = productPriceRepo.findById(id);
        if (!optProductPrice.isPresent()) {
            throw new ProductNotFoundException("Product Price not available.");
        }

        return new ProductResponse(id, productDetails.getName(), optProductPrice.get());
    }

    /**
     * @param request product details request
     * @return product details response
     */
    public ProductResponse updateProductDetails(ProductResponse request) {
        log.info("In updateProductDetails()");
        ProductDetails pdResponse = productWebClient.retrieveProductName(request.getId());
        request.setName(pdResponse.getName());

        log.info("updating product price in db");
        ProductPrice pPrice = request.getCurrent_price();
        pPrice.setId(request.getId());
        productPriceRepo.save(pPrice);

        return request;
    }

}
