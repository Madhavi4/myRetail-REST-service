package com.myretail.casestudy.service;

import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.exceptions.ProductServiceException;
import com.myretail.casestudy.model.ProductApiResponse;
import com.myretail.casestudy.model.ProductDetails;
import com.myretail.casestudy.model.ProductPrice;
import com.myretail.casestudy.repository.ProductPriceRepository;
import com.myretail.casestudy.web.ProductWebClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Test class for ProductService
 */
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductPriceRepository productPriceRepo;
    @Mock
    private ProductWebClient productWebClient;
    @InjectMocks
    private ProductService productService;

    private ProductApiResponse pdtApiResponse = new ProductApiResponse();
    private ProductPrice productPrice = new ProductPrice();
    private ProductDetails productDetails = new ProductDetails();
    private Long id = 13860428L;
    private Double value = 24.99;
    private String name = "Finding Nemo (Blu-Ray + DVD + Digital)";

    /**
     * method to run before the tests for initial setup
     */
    @Before
    public void setUp() {
        pdtApiResponse.setId(id);
        pdtApiResponse.setName(name);
        productPrice.setId(id);
        productPrice.setCurrency_code("USD");
        productPrice.setValue(value);

        productDetails.setId(id);
        productDetails.setName("test name");
        productDetails.setCurrent_price(productPrice);
    }

    /**
     * Test for getProductDetails Success
     */
    @Test
    public void testGetProductDetails() throws ProductServiceException {
        when(productWebClient.retrieveProductName(eq(id))).thenReturn(pdtApiResponse);
        when(productPriceRepo.findById(eq(id))).thenReturn(Optional.of(productPrice));
        ProductDetails response = productService.getProductDetails(id);

        assertEquals(response.getId(), id);
        assertEquals(response.getName(), name);
        assertEquals(response.getCurrent_price().getCurrency_code(), "USD");
        assertEquals(response.getCurrent_price().getValue(), value);
    }

    /**
     * Test for getProductDetails throws ProductNotFoundException
     */
    @Test(expected = ProductNotFoundException.class)
    public void testGetProductDetails_throws_not_found_exception() throws ProductServiceException {
        when(productWebClient.retrieveProductName(eq(id))).thenReturn(pdtApiResponse);
        when(productPriceRepo.findById(eq(id))).thenReturn(Optional.ofNullable(null));
        productService.getProductDetails(id);
    }

    /**
     * Test for updateProductDetails Success
     */
    @Test
    public void testUpdateProductDetails() throws ProductServiceException {
        when(productWebClient.retrieveProductName(eq(id))).thenReturn(pdtApiResponse);
        ProductDetails response = productService.updateProductDetails(productDetails);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getCurrent_price()).isNotNull();
        assertThat(response.getCurrent_price().getCurrency_code()).isEqualTo("USD");
        assertThat(response.getCurrent_price().getValue()).isNotNull();
        assertThat(response.getCurrent_price().getId()).isEqualTo(id);
    }
}