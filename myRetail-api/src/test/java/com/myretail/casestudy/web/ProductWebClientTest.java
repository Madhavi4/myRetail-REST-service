package com.myretail.casestudy.web;

import com.myretail.casestudy.json.ProductDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
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
    private String redskyUrl = "https://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
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
    public void testRetrieveProductName() {

        try {
            Path productJsonPath = Paths.get(new ClassPathResource("json/product_13860428.json").getURI());
            String productJson = new String(Files.readAllBytes(productJsonPath), StandardCharsets.UTF_8);
            when(redskyRestTemplate.getForEntity(eq(redskyUrl), eq(String.class))).thenReturn(new ResponseEntity<>(productJson, HttpStatus.OK));

            ProductDetails pd = productWebClient.retrieveProductName(id);
            Assert.assertEquals(pd.getId(), id);
            Assert.assertEquals(pd.getName(), "The Big Lebowski (Blu-ray)");

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * test retrieveProductName for Exception
     */
    @Test
    public void testRetrieveProductNameException() {
        when(redskyRestTemplate.getForEntity(eq(redskyUrl), eq(String.class))).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        thrown.expect(ResponseStatusException.class);
        thrown.expectMessage(containsString("No results for path:"));
        productWebClient.retrieveProductName(id);
    }
}