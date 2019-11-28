package com.myretail.casestudy.service;

import com.myretail.casestudy.json.ProductDetails;
import com.myretail.casestudy.json.ProductResponse;
import com.myretail.casestudy.model.ProductPrice;
import com.myretail.casestudy.repository.ProductPriceRepository;
import com.myretail.casestudy.web.ProductWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public ProductResponse getProductDetails(Integer id) {
        log.info("In getProductDetails()");
        ProductDetails productDetails = productWebClient.retrieveProductName(id);

        Optional<ProductPrice> optProductPrice = productPriceRepo.findById(id);
        if (!optProductPrice.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ProductResponse(id, productDetails.getName(), optProductPrice.get());
    }

    /**
     * @param productResponse product details
     * @return product details
     */
    public ProductResponse updateProductDetails(ProductResponse productResponse) {
        log.info("In updateProductDetails()");
        ProductDetails pdResponse = productWebClient.retrieveProductName(productResponse.getId());

        ProductPrice pPrice = productResponse.getCurrent_price();
        pPrice.setId(productResponse.getId());
        productPriceRepo.save(pPrice);

        return productResponse;
    }

}
