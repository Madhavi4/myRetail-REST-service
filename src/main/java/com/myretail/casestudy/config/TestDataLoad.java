package com.myretail.casestudy.config;

import com.myretail.casestudy.model.ProductPrice;
import com.myretail.casestudy.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestDataLoad {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @PostConstruct
    public void loadTestPriceDetails() {
        ProductPrice productPrice1 = new ProductPrice(54498967L, 57.99, "USD");
        ProductPrice productPrice2 = new ProductPrice(76780149L, 59.99, "USD");
        ProductPrice productPrice3 = new ProductPrice(76593324L, 79.99, "USD");
        productPriceRepository.save(productPrice1);
        productPriceRepository.save(productPrice2);
        productPriceRepository.save(productPrice3);
    }
}
