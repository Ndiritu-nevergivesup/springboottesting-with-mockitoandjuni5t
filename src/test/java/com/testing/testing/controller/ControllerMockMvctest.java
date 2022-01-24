package com.testing.testing.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.testing.Service.TestService;
import com.testing.testing.model.Country;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.testing.testing")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockMvctest.class})
public class ControllerMockMvctest {

    List<Country> countries;
    Country country;

    @Autowired
    MockMvc mockMvc;

    @Mock
    TestService testService;
    @InjectMocks
    Controller controller;

    @BeforeEach
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    @Order(1)
    public void testgetallcountries() throws Exception{
         countries=new ArrayList<Country>(0);
        countries.add(new Country(1L,"Algeria"));
        countries.add(new Country(2L,"liberia"));
        countries.add(new Country(3L,"Nigeria"));
       when(testService.getallc()).thenReturn(countries);
       this.mockMvc.perform(MockMvcRequestBuilders.get("/getcountries")).andExpect(status().isFound()).andDo(print());
}

@Test
 @Order(2)
 public void getcountrybyid() throws Exception {
    country=new Country(1L,"Eritrea");
//    country=new Country(2L,"Djibouti");
         when(testService.getcountryById(1L)).thenReturn(country);
         this.mockMvc.perform(MockMvcRequestBuilders.get("/getcountrybyid/{id}",1)).andExpect(status()
         .isFound())
                 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
         .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Eritrea")))
         .andDo(print());


}
    @Test
    @Order(3)
    public void getcountrybyname() throws Exception {
//        country=new Country(1L,"Eritrea");
    country=new Country(2L,"Djibouti");
        when(testService.getcountryByName("Djibouti")).thenReturn(country);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/getcountrybyname/{name}","Djibouti")).andExpect(status()
                .isFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Djibouti")))
                .andDo(print());
    }

    @Test
    @Order(5)
    public void addcountry() throws Exception {
        country=new Country(4L,"Malawi");
        country=new Country(5L,"Botswana");
        when(testService.addcountry(country)).thenReturn(country);
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonbody= objectMapper.writeValueAsString(country);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/ADD").content(jsonbody).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
    }

  @Test
    @Order(4)
    public void  deletebyid() throws Exception {
        country=new Country(6L,"Bulgaria");
        when(testService.getcountryById(6L)).thenReturn(country);
        mockMvc.perform(MockMvcRequestBuilders.delete("/{id}",6)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
  }
}
