package com.example.demo.mainModule;

import com.example.demo.models.CountryInfo;
import com.example.demo.repositories.CountryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.demo.mainModule.SendRequest.send;

@Component
public class CoronaMonitorAppender {
    @Autowired
    private CountryInfoRepository countryInfoRepository;

    @Scheduled( initialDelay = 60 * 1000, fixedDelay = 60 * 1000)
    public void createOrUpdateCountriesInfo() {
        List<CountryInfo> countryInfos = getCountriesInfo();
        updateBd(countryInfos);

    }

    public static List<CountryInfo> getCountriesInfo() {
        String resp = send("https://coronavirus-monitor.info/");
        resp = resp.split("Статистика заражений коронавирусом по странам на")[1];
        String[] split_res = resp.split("<div class=\"flex-table\" role=\"rowgroup\">");
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(split_res));
        ArrayList<CountryInfo> countryInfos = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            String country = arr.get(i);
            if (country.contains("data-country=\"")) {
                String countryName = country.split("data-country=\"")[1].split("\"")[0];
                String infected = country.split("data-confirmed=\"")[1].split("\"")[0];
                String cured = country.split("data-cured=\"")[1].split("\"")[0];
                String critical = country.split("data-critical=\"")[1].split("\"")[0];
                String deaths = country.split("data-deaths=\"")[1].split("\"")[0];
                CountryInfo obj = CountryInfo.
                        builder().
                        countryName(countryName).
                        infected(Integer.parseInt(infected)).
                        cured(Integer.parseInt(cured)).
                        critical(Integer.parseInt(critical)).
                        died(Integer.parseInt(deaths))
                        .build();
                countryInfos.add(obj);
            }

        }
        return countryInfos;
    }

    public void updateBd(List<CountryInfo> countryInfos) {
        for (int i = 0; i < countryInfos.size(); i++) {
            CountryInfo newCountry = countryInfos.get(i);
            Optional<CountryInfo> oldElem = countryInfoRepository.findByCountryName(newCountry.getCountryName());
            if (oldElem.isPresent()) {
                CountryInfo oldObj = oldElem.get();
                oldObj.setCritical(newCountry.getCritical());
                oldObj.setCured(newCountry.getCured());
                oldObj.setDied(newCountry.getDied());
                oldObj.setInfected(newCountry.getInfected());
                countryInfoRepository.save(oldObj);
            } else {
                countryInfoRepository.save(newCountry);
            }
        }
    }
}
