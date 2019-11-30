package com.myretail.casestudy.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.PathNotFoundException;
import com.myretail.casestudy.model.ProductApiResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;

public class ProductApiResponseDeserializerTest {

    String productJson;
    private ProductApiResponseDeserializer productApiResponseDeserializer;

    @Before
    public void setup() throws IOException {
        productApiResponseDeserializer = new ProductApiResponseDeserializer();
        Path productJsonPath = Paths.get(new ClassPathResource("json/product_13860428.json").getURI());
        productJson = new String(Files.readAllBytes(productJsonPath), StandardCharsets.UTF_8);
    }

    @Test
    public void testDeserialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ProductApiResponse productApiResponse = mapper.readValue(productJson, ProductApiResponse.class);
        Assert.assertNotNull(productApiResponse);
        Assert.assertEquals(productApiResponse.getId(), Long.valueOf(13860428));
        Assert.assertEquals(productApiResponse.getName(), "The Big Lebowski (Blu-ray)");
    }


    @Test
    public void testDeserialize() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        DeserializationContext deserializationContext = mock(DeserializationContext.class);
        TreeNode treeNode = mock(TreeNode.class);

        when(jsonParser.readValueAsTree()).thenReturn(treeNode);
        when(treeNode.toString()).thenReturn(productJson);

        ProductApiResponse productApiResponse = productApiResponseDeserializer.deserialize(jsonParser, deserializationContext);

        Mockito.verify(jsonParser, times(1)).readValueAsTree();

        Assert.assertNotNull(productApiResponse);
        Assert.assertEquals(productApiResponse.getId(), Long.valueOf(13860428));
        Assert.assertEquals(productApiResponse.getName(), "The Big Lebowski (Blu-ray)");
    }

    @Test(expected = PathNotFoundException.class)
    public void testDeserialize_throws_exception_on_invalid_json() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        DeserializationContext deserializationContext = mock(DeserializationContext.class);
        TreeNode treeNode = mock(TreeNode.class);

        when(jsonParser.readValueAsTree()).thenReturn(treeNode);
        when(treeNode.toString()).thenReturn("{\"product\":{\"item\":{}}}");

        productApiResponseDeserializer.deserialize(jsonParser, deserializationContext);
    }
}