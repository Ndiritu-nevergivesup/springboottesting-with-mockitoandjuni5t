package com.testing.testing.controller;

import com.sun.net.httpserver.Headers;
import com.testing.testing.model.Country;
import org.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest(classes = {ControllerIntegrationtesting.class})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerIntegrationtesting {
 TestRestTemplate testRestTemplate;

    @Test
    @Order(2)
   public void getallcountries() throws JSONException {

        String expected="[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Kenya\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Uganda\"\n" +
                "    }\n" +
                "]";

        testRestTemplate=new TestRestTemplate();
//        TestRestTemplate testRestTemplate=new TestRestTemplate();
         ResponseEntity<String> responseEntity=testRestTemplate.getForEntity("http://localhost:8080/getcountries",String.class);
           System.out.println(responseEntity.getStatusCode());
           System.out.println(responseEntity.getBody());
        JSONAssert.assertEquals(expected,responseEntity.getBody(),false);
    }
    @Test
    @Order(1)
  public void getcountrybyid() throws JSONException {
        String expected="{\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Uganda\"\n" +
                "}";
        testRestTemplate=new TestRestTemplate();
//        TestRestTemplate testRestTemplate=new TestRestTemplate()
         ResponseEntity<String > responseEntity=testRestTemplate.getForEntity("http://localhost:8080/getcountrybyid/2",String.class);
         System.out.println(responseEntity.getStatusCode());
         System.out.println(responseEntity.getBody());
         JSONAssert.assertEquals(expected,responseEntity.getBody(),false);

    }
    @Test
    @Order(3)
    public void addcountry() throws JSONException {
        Country country=new Country(5L,"India");
        String expected="{\n" +
                "    \"id\": 5,\n" +
                "    \"name\": \"India\"\n" +
                "}";
        testRestTemplate=new TestRestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Country> httpEntity=new HttpEntity<Country>(country,headers);
        ResponseEntity<String> responseEntity=testRestTemplate.postForEntity("http://localhost:8080/ADD",httpEntity,String.class);

        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        JSONAssert.assertEquals(expected,responseEntity.getBody(),false);

    }
    @Test
    @Order(4)
    public void deletecountry() throws JSONException {
        Country country=new Country(5L,"India");
        String expected="{\n" +
                "    \"id\": 5,\n" +
                "    \"name\": \"India\"\n" +
                "}";
        testRestTemplate=new TestRestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Country> httpEntity=new HttpEntity<Country>(country,headers);
        ResponseEntity<String> responseEntity=testRestTemplate.exchange("http://localhost:8080/5",HttpMethod.DELETE,httpEntity,String.class);

//        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        JSONAssert.assertEquals(expected,responseEntity.getBody(),false);

    }
}
