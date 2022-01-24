package com.testing.testing.Service;

import com.testing.testing.Repository.TestRepository;
import com.testing.testing.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    public List<Country> getallc() {
        List<Country> countries= new ArrayList<>(testRepository.findAll());

       return countries;

    }
    public Country getcountryById(Long id){
        List<Country> countries=testRepository.findAll();
        Country coun=null;
        for (Country co:countries){
            if(co.getId().equals(id)){
                 coun=co;
            }
        }
            return coun;
    }
    public Country getcountryByName(String name){
        List<Country> countries=testRepository.findAll();
        Country coun=null;
        for (Country co:countries){
            if(co.getName().equalsIgnoreCase(name)){
                coun=co;
            }
        }
        return coun;
    }
    public Country addcountry(Country country){
         Country c=new Country();
         c.setId(country.getId());
         c.setName(country.getName());
        return testRepository.save(c);
    }
    public void deletecountry(Country country){
         testRepository.delete(country);
    }
}
