package com.myretail.casestudy.web;

import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.exceptions.ProductServiceException;
import com.myretail.casestudy.model.ProductApiResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Test class for ProductWebClient
 */
@RunWith(SpringRunner.class)
public class ProductWebClientTest {
    /**
     * Rule to catch exceptions
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private RestTemplate redskyRestTemplate;
    @InjectMocks
    private ProductWebClient productWebClient;
    private Long id = 13860428L;
    private String redskyUrl = "https://redsky.target.com/v2/pdp/tcin/{product_id}";
    private String expectedUrl = "https://redsky.target.com/v2/pdp/tcin/13860428";
    private String response = "{\"product\":{\"item\":{}}}";

    /**
     * method to run before the tests for initial setup
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(productWebClient, "redskyUrl", redskyUrl);
    }

    /**
     * test retrieveProductName for success
     */
    @Test
    public void testRetrieveProductName() throws ProductServiceException {

        ProductApiResponse productApiResponse = new ProductApiResponse(id, "The Big Lebowski (Blu-ray)");
        when(redskyRestTemplate.getForEntity(eq(expectedUrl), eq(ProductApiResponse.class))).thenReturn(new ResponseEntity<>(productApiResponse, HttpStatus.OK));

        ProductApiResponse pd = productWebClient.retrieveProductName(id);
        Assert.assertEquals(pd, productApiResponse);
    }

    @Test
    public void testRetrieveProductName_throws_service_exception() throws ProductServiceException{
        ProductServiceException expected = new ProductServiceException("failed retrieving product");
        when(redskyRestTemplate.getForEntity(eq(expectedUrl), eq(ProductApiResponse.class))).thenThrow(new RestClientException("test"));

        thrown.expect(ProductServiceException.class);
        thrown.expectMessage(expected.getMessage());

        productWebClient.retrieveProductName(id);
    }

    @Test
    public void testRetrieveProductName_throws_status_404_exception() throws ProductServiceException {
        HttpStatusCodeException ex = new HttpClientErrorException(HttpStatus.NOT_FOUND);
        ProductNotFoundException expected = new ProductNotFoundException("Product not available for productId: 13860428");
        when(redskyRestTemplate.getForEntity(eq(expectedUrl), eq(ProductApiResponse.class))).thenThrow(ex);

        thrown.expect(ProductNotFoundException.class);
        thrown.expectMessage(expected.getMessage());

        productWebClient.retrieveProductName(id);
    }

    @Test
    public void testRetrieveProductName_throws_status_400_exception() throws ProductServiceException{
        HttpStatusCodeException ex = new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        ProductServiceException expected = new ProductServiceException("failed retrieving product");
        when(redskyRestTemplate.getForEntity(eq(expectedUrl), eq(ProductApiResponse.class))).thenThrow(ex);

        thrown.expect(ProductServiceException.class);
        thrown.expectMessage(expected.getMessage());

        productWebClient.retrieveProductName(id);
    }
}