package com.testing.testing.Service;

import com.testing.testing.Repository.TestRepository;
import com.testing.testing.model.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {TestServiceTest.class})
class TestServiceTest {

    @Mock
    TestRepository testRepository;


    @InjectMocks
    TestService testService;


    @Test
    @DisplayName("Test should display all country names")
    void getallcountries() {

        List<Country> tcountries=new ArrayList<Country>(0);
        tcountries.add(new Country(1L,"Kenya"));
        tcountries.add(new Country(2L,"Tanzania"));
        tcountries.add(new Country(3L,"Uganda"));
        tcountries.add(new Country(4L,"Somalia"));
        tcountries.add(new Country(5L,"Rwanda"));

        when(testRepository.findAll()).thenReturn(tcountries);
        assertEquals(5,testService.getallc().size());

    }

    @Test
    void getcountryById() {
        List<Country> testcountries=new ArrayList<>(0);
        testcountries.add(new Country(6L,"Botswana"));
        testcountries.add(new Country(7L,"Namimbia"));
        testcountries.add(new Country(8L,"Nigeria"));
        testcountries.add(new Country(9L,"Malawi"));
        testcountries.add(new Country(10L,"India"));
        testcountries.add(new Country(11L,"SouthAfrica"));

        when(testRepository.findAll()).thenReturn(testcountries);
        assertEquals(6L,testService.getcountryById(6L).getId());

    }

    @Test
    void getcountryByName() {

        List<Country> testcountries=new ArrayList<>(0);
        testcountries.add(new Country(6L,"Botswana"));
        testcountries.add(new Country(7L,"Namimbia"));
        testcountries.add(new Country(8L,"Nigeria"));
        testcountries.add(new Country(9L,"Malawi"));
        testcountries.add(new Country(10L,"India"));
        testcountries.add(new Country(11L,"SouthAfrica"));
        when(testRepository.findAll()).thenReturn(testcountries);
        System.out.println("Countriesbyname "+testService.getcountryByName("Botswana"));
        assertEquals("Botswana",testService.getcountryByName("Botswana").getName());
    }

    @Test
    void addcountry() {
        Country country= new Country(10L,"Iran");
        when(testRepository.save(country)).thenReturn(country);
        assertEquals(country,testService.addcountry(country));


    }

    @Test
    void deletecountry() {
        Country country= new Country(10L,"Iran");

                testService.deletecountry(country);
                verify(testRepository,times(1)).delete(country);
    }
}