package com.testing.testing.controller;

import com.testing.testing.Service.TestService;
import com.testing.testing.model.Country;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ControllerTest.class})
class ControllerTest {

    @Mock
    TestService testService;

    @InjectMocks
    Controller controller;
    List<Country> mycountries;
    Country country;
    @Test
  public   void getallcountries() {
      mycountries=new ArrayList<>(0);
        mycountries.add(new Country(6L,"Botswana"));
        mycountries.add(new Country(7L,"Kenya"));
        mycountries.add(new Country(8L,"Somalia"));
        mycountries.add(new Country(9L,"Mauritius"));
        mycountries.add(new Country(10L,"zambia"));


        when(testService.getallc()).thenReturn(mycountries);
        ResponseEntity<List<Country>> response=controller.getallcountries();
             assertEquals(HttpStatus.FOUND,response.getStatusCode());
             assertEquals(5,response.getBody().size());
    }

    @Test
    void getcountrybyid() {
        country=new Country(1L,"USA");
        country=new Country(2L,"costa rica");
        when(testService.getcountryById(1L)).thenReturn(country);
        ResponseEntity<Country> response=controller.getcountrybyid(1L);
        assertEquals(HttpStatus.FOUND,response.getStatusCode());
        assertEquals(2L,response.getBody().getId());
    }

    @Test
    void getcountrybyname() {
        country=new Country(1L,"USA");
        country=new Country(2L,"costa rica");
        when(testService.getcountryByName("costa rica")).thenReturn(country);
        ResponseEntity<Country> response=controller.getcountrybyname("costa rica");
        assertEquals(HttpStatus.FOUND,response.getStatusCode());
        assertEquals("costa rica",response.getBody().getName());
    }

    @Test
    void addcountry() {
        country=new Country(1L,"USA");
        country=new Country(2L,"costa rica");
        when(testService.addcountry(country)).thenReturn(country);
        ResponseEntity<Country> responseEntity=controller.addcountry(country);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals("costa rica",responseEntity.getBody().getName());
    }

    @Test
    void deletecountry() {
        country=new Country(1L,"USA");
        country=new Country(2L,"costa rica");
        when(testService.getcountryById(1L)).thenReturn(country);
        ResponseEntity<Country> RES=controller.deletecountry(1L);
        assertEquals(HttpStatus.OK,RES.getStatusCode());
    }
}