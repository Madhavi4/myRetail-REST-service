package com.myretail.casestudy.controller;

import com.myretail.casestudy.exceptions.ProductServiceException;
import com.myretail.casestudy.model.ProductDetails;
import com.myretail.casestudy.model.ProductPrice;
import com.myretail.casestudy.service.ProductService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test class for ProductController
 */
public class ProductControllerTest {

    /**
     * Rule to catch exceptions
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;

    private ProductDetails productDetails = new ProductDetails();
    private Long id = 7675824599L;

    /**
     * method to run before the tests for initial setup
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test retrieveProductDetails for Success
     */
    @Test
    public void testRetrieveProductDetails() throws ProductServiceException {
        ProductDetails expected = Mockito.mock(ProductDetails.class);
        when(productService.getProductDetails(id)).thenReturn(expected);
        ResponseEntity<ProductDetails> response = productController.retrieveProductDetails(id);
        verify(productService, times(1)).getProductDetails(id);
        assertEquals(expected, response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRetrieveProductDetails_throws_exception() throws ProductServiceException {
        ProductServiceException expected = new ProductServiceException("test!!!");
        when(productService.getProductDetails(id)).thenThrow(expected);
        thrown.expect(ProductServiceException.class);
        thrown.expectMessage(expected.getMessage());
        productController.retrieveProductDetails(id);
        verify(productService, times(1)).getProductDetails(id);
    }

    /**
     * test updateProductDetails for Success
     */
    @Test
    public void testUpdateProductDetailsById() throws ProductServiceException {
        productDetails.setId(id);
        productDetails.setCurrent_price(new ProductPrice(10.99, "USD"));

        when(productService.updateProductDetails(eq(productDetails))).thenReturn(productDetails);
        EntityModel<ProductDetails> response = productController.updateProductDetailsById(id, productDetails);

        verify(productService, times(1)).updateProductDetails(productDetails);

        assertNotNull(response.getContent());
        assertEquals(response.getContent().getId(), id);
    }
}