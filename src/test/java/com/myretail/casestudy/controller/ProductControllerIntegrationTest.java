package com.myretail.casestudy.controller;

import com.jayway.jsonpath.JsonPath;
import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.exceptions.ProductServiceException;
import com.myretail.casestudy.handler.CustomRestExceptionHandler;
import com.myretail.casestudy.model.ProductDetails;
import com.myretail.casestudy.model.ProductPrice;
import com.myretail.casestudy.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
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

    private ProductDetails productDetails = new ProductDetails();
    private Long id = 52978799L;
    private String productJsonInvalid = "{\"id\":7675824599}";
    private String productJson = "{\"id\":52978799,\"name\":\"Apple Watch Series 3 (GPS) 38mm Aluminum Case\",\"current_price\":{\"value\":169.99,\"currency_code\":\"USD\"}}";

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
    public void testRetrieveProductDetails_throws_not_found_exception() throws Exception {
        when(productService.getProductDetails(id)).thenThrow(new ProductNotFoundException("Price not available"));

        mockMvc.perform(get("/v1/products/" + id)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * test retrieveProductDetails throws Exception while retrieving productDetails from redsky productDetails api
     *
     * @throws Exception
     */
    @Test
    public void testRetrieveProductDetails_throws_service_exception() throws Exception {
        when(productService.getProductDetails(id)).thenThrow(new ProductServiceException("failed retrieving productDetails"));

        mockMvc.perform(get("/v1/products/" + id)).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    /**
     * test UpdateProductDetailsById for success
     *
     * @throws Exception
     */
    @Test
    public void testUpdateProductDetailsById() throws Exception {
        setUpProductDetails();
        when(productService.updateProductDetails(Mockito.any(ProductDetails.class))).thenReturn(productDetails);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/v1/products/" + id)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(productJson);

        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href").value("http://localhost/v1/products/52978799"));
    }

    /**
     * test UpdateProductDetailsById throws Exception for id mismatch between path and request body
     *
     * @throws Exception
     */
    @Test
    public void testUpdateProductDetailsById_throws_id_mismatch_exception() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/v1/products/7675824599")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(productJson);

        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The id in the path and the payload should match"));
    }

    /**
     * test UpdateProductDetailsById throws Exception for parameter validation
     *
     * @throws Exception
     */
    @Test
    public void testUpdateProductDetailsById_throws_field_validation_exception() throws Exception {

        String[] expected = {"name: Product name is mandatory", "current_price: Product price is mandatory"};
        Arrays.sort(expected);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/v1/products/" + id)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(productJsonInvalid);

        MvcResult mvcResult = this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        String errorMessage = JsonPath.parse(responseBody).read("$.message");
        String[] actual = errorMessage.split(", ");
        Arrays.sort(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    public void setUpProductDetails() {
        productDetails.setId(id);
        productDetails.setName("Apple Watch Series 3 (GPS) 38mm Aluminum Case");
        productDetails.setCurrent_price(new ProductPrice(169.99, "USD"));
    }
}