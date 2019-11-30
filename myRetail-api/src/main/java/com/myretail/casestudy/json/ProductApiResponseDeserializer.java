package com.myretail.casestudy.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.jayway.jsonpath.JsonPath;
import com.myretail.casestudy.model.ProductApiResponse;

import java.io.IOException;

public class ProductApiResponseDeserializer extends StdDeserializer<ProductApiResponse> {

    public static final String PRODUCT_ID_PATH = "$.product.item.tcin";
    public static final String PRODUCT_NAME_PATH = "$.product.item.product_description.title";

    public ProductApiResponseDeserializer() {
        this(null);
    }

    protected ProductApiResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProductApiResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String responseJson = jsonParser.readValueAsTree().toString();
        Long id = Long.parseLong(JsonPath.parse(responseJson).read(PRODUCT_ID_PATH));
        String name = JsonPath.parse(responseJson).read(PRODUCT_NAME_PATH);
        return new ProductApiResponse(id, name);
    }
}
