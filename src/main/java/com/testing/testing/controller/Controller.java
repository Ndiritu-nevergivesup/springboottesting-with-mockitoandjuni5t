package com.testing.testing.controller;

import com.testing.testing.Service.TestService;
import com.testing.testing.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@AllArgsConstructor
public class Controller {

    private final TestService testService;

    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getallcountries() {
        try {
            List<Country> countries = testService.getallc();
            return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountrybyid/{id}")
    public ResponseEntity<Country> getcountrybyid(@PathVariable("id") Long id) {
        try {
            Country country = testService.getcountryById(id);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("getcountrybyname/{name}")
    public ResponseEntity<Country> getcountrybyname(@PathVariable("name") String name) {
        try {
            Country country = testService.getcountryByName(name);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    @PostMapping("/ADD")
    public ResponseEntity<Country> addcountry(@RequestBody Country name) {
        try {
            Country country = testService.addcountry(name);
            return new ResponseEntity<Country>(country, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
    }
        @DeleteMapping("/{id}")
        public ResponseEntity<Country> deletecountry(@PathVariable("id") Long id){
                Country country;
            try {
                        country=testService.getcountryById(id);
                        testService.deletecountry(country);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.CONFLICT);

            }
                return new ResponseEntity<Country>(country,HttpStatus.OK);
}



}
