package com.myretail.casestudy.controller;

import com.myretail.casestudy.json.ProductResponse;
import com.myretail.casestudy.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ProductControllerTest {
    @MockBean
    private ProductService productService;
    @InjectMocks
    private ProductController productController;

    private ProductResponse productResponse = new ProductResponse();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveProductDetails() {
        productController.retrieveProductDetails(1234);
        verify(productService, times(1)).getProductDetails(1234);
    }

    @Test
    public void testUpdateProductDetailsById() {
        productController.updateProductDetailsById(234, productResponse);
        verify(productService, times(1)).updateProductDetails(productResponse);
    }
}