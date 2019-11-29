package com.myretail.casestudy.controller;

import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.handler.CustomRestExceptionHandler;
import com.myretail.casestudy.json.ProductResponse;
import com.myretail.casestudy.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Test class for ProductController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
    @MockBean
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @InjectMocks
    private CustomRestExceptionHandler exceptionHandler;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    private ProductResponse productResponse = new ProductResponse();
    private Long id = 7675824599L;

    /**
     * method to run before the tests for initial setup
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * test retrieveProductDetails throws Exception for missing price
     *
     * @throws Exception
     */
    @Test
    public void testRetrieveProductDetailsException() throws Exception {
        when(productService.getProductDetails(id)).thenThrow(new ProductNotFoundException("Price not available"));

        mockMvc.perform(get("/v1/products/" + id)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * test retrieveProductDetails throws Exception for missing product
     *
     * @throws Exception
     */
    @Test
    public void testRetrieveProductDetailsExceptionWithCause() throws Exception {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.NOT_FOUND);
        when(productService.getProductDetails(id)).thenThrow(new ProductNotFoundException("Product not found", ex));

        mockMvc.perform(get("/v1/products/" + id)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * test retrieveProductDetails for Success
     */
    @Test
    public void testRetrieveProductDetailsSuccess() {
        productController.retrieveProductDetails(id);
        verify(productService, times(1)).getProductDetails(id);
    }

    /**
     * test updateProductDetails for Success
     */
    @Test
    public void testUpdateProductDetailsByIdSuccess() {
        productController.updateProductDetailsById(id, productResponse);
        verify(productService, times(1)).updateProductDetails(productResponse);
    }
}