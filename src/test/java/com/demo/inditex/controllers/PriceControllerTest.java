package com.demo.inditex.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPrice_First() throws Exception {
        UriComponents uri = UriComponentsBuilder
                .fromUriString("/price")
                .queryParam("productId","35455")
                .queryParam("brandId",1)
                .queryParam("rateStartDate", "2020-06-14T10:00:00")
                .build();

        RequestBuilder request = MockMvcRequestBuilders.get(uri.toUriString());
        MvcResult mvcResult = mvc.perform(request).andReturn();
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getPrice_Second() throws Exception {
        UriComponents uri = UriComponentsBuilder
                .fromUriString("/price")
                .queryParam("productId","35455")
                .queryParam("brandId",1)
                .queryParam("rateStartDate", "2020-06-14T16:00:00")
                .build();

        RequestBuilder request = MockMvcRequestBuilders.get(uri.toUriString());
        MvcResult mvcResult = mvc.perform(request).andReturn();
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getPrice_Third() throws Exception {
        UriComponents uri = UriComponentsBuilder
                .fromUriString("/price")
                .queryParam("productId","35455")
                .queryParam("brandId",1)
                .queryParam("rateStartDate", "2020-06-14T21:00:00")
                .build();

        RequestBuilder request = MockMvcRequestBuilders.get(uri.toUriString());
        MvcResult mvcResult = mvc.perform(request).andReturn();
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test(expected = NestedServletException.class)
    public void getPrice_BadPeticion() throws Exception {
        UriComponents uri = UriComponentsBuilder
                .fromUriString("/price")
                .queryParam("productId","35455")
                .queryParam("brandId",1)
                .queryParam("rateStartDate", "")
                .build();

        RequestBuilder request = MockMvcRequestBuilders.get(uri.toUriString());
        mvc.perform(request)
                .andReturn();
    }
}