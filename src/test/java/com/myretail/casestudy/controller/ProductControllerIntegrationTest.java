package com.myretail.casestudy.controller;

import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.handler.CustomRestExceptionHandler;
import com.myretail.casestudy.model.ProductDetails;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerIntegrationTest {

    @MockBean
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @InjectMocks
    private CustomRestExceptionHandler exceptionHandler;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    private ProductDetails product = new ProductDetails();
    private Long id = 7675824599L;
    private String productJson = "{\"id\": 52978799,\"current_price\": {\"value\": 169.99,\n" +
            "        \"currency_code\": \"USD\"} }";

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
     * test UpdateProductDetailsById throws Exception for missing product name
     *
     * @throws Exception
     */
    @Test
    public void testUpdateProductDetailsByIdExceptionWithCause() throws Exception {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.NOT_FOUND);
        when(productService.getProductDetails(id)).thenThrow(new ProductNotFoundException("Product not found", ex));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/v1/products/" + id)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(productJson);

        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}