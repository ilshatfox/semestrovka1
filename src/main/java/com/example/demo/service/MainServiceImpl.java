package com.example.demo.service;

import com.example.demo.mainModule.CoronaMonitorAppender;
import com.example.demo.models.CountryInfo;
import com.example.demo.repositories.CountryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainServiceImpl implements MainService{

    @Autowired
    private CountryInfoRepository countryInfoRepository;

    @Autowired
    CoronaMonitorAppender coronaMonitorAppender;

    public List<CountryInfo> coronaMonitor() {
        List<CountryInfo> elems = countryInfoRepository.findAllCountryInfo();
        elems.sort((o1, o2) -> o2.getInfected().compareTo(o1.getInfected()));
        return elems;
    }

}
