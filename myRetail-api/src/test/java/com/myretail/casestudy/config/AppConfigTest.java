package com.myretail.casestudy.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

public class AppConfigTest {

    private AppConfig appConfig;

    @Before
    public void setup() {
        appConfig = new AppConfig();
    }

    @Test
    public void testRestTemplateCreation() {
        RestTemplateBuilder restTemplateBuilder = Mockito.mock(RestTemplateBuilder.class);
        RestTemplate expected = new RestTemplate();
        when(restTemplateBuilder.build()).thenReturn(expected);
        RestTemplate restTemplate = appConfig.redskyRestTemplate(restTemplateBuilder);
        Assert.assertNotNull(restTemplate);
        Assert.assertEquals(restTemplate, expected);
    }
}