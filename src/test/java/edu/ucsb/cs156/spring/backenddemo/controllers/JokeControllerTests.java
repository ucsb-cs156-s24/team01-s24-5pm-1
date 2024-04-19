package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(value = JokeController.class)
public class JokeControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  JokeQueryService mockJokeQueryService;

  @Test
  public void test_getJoke() throws Exception {
  
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    int amount = 3;
    String category = "Programming";
    when(JokeQueryService.getJSON(eq(category), eq(amount))).thenReturn(fakeJsonResult);
    String url = String.format("/joke/%s?amount=%s", category, amount + "");

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}