package com.maha.controller;

import com.maha.checkout.Application;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = Application.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CheckoutControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void checkout_empty_product() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[]")
    ).andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Is.is("Products must not be empty")));
  }

  @Test
  public void checkout_product1_quantity1() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"001\"]")
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(100)));
  }

  @Test
  public void checkout_product1_quantity2() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"001\", \"001\"]")
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(200)));
  }

  @Test
  public void checkout_product1_quantity3() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"001\", \"001\", \"001\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(200)));
  }

  @Test
  public void checkout_product2_quantity5() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"002\", \"002\", \"002\", \"002\", \"002\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(320)));
  }

  @Test
  public void checkout_product2_quantity8() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"002\", \"002\", \"002\", \"002\", \"002\", \"002\", \"002\", \"002\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(480)));
  }

  @Test
  public void checkout_product3_quantity3() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"003\", \"003\", \"003\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(150)));
  }

  @Test
  public void checkout_product4_quantity9() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"004\", \"004\", \"004\", \"004\", \"004\", \"004\", \"004\", \"004\", \"004\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(270)));
  }

  @Test
  public void checkout_multi_product_1() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"001\", \"002\", \"003\", \"004\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(260)));
  }

  @Test
  public void checkout_multi_product_2() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"001\", \"002\", \"003\", \"004\", \"003\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(310)));
  }

  @Test
  public void checkout_multi_product_3() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[\"001\", \"002\", \"001\", \"004\", \"003\"]" )
    ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(360)));
  }

}
