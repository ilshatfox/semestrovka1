package com.example.demo.controllers;

import com.example.demo.dto.CountryInfoDao;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserDaoImpl;
import com.example.demo.mainModule.CoronaMonitorAppender;
import com.example.demo.models.CountryInfo;
import com.example.demo.models.User;
import com.example.demo.repositories.CountryInfoRepository;
import com.example.demo.service.MainService;
import com.example.demo.service.SignInUpService;
//import com.example.demo.translater.LgTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    CoronaMonitorAppender coronaMonitorAppender;

    @Autowired
    private MainService mainService;

    @GetMapping("/result/{message}")
    public String result(Model model, @PathVariable("message") String message) {
        model.addAttribute("message", message);
        return "result_page";
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        return "redirect:/coronavirus";
    }

    @GetMapping("/coronavirus")
    public String aboutCoronavirus(Model model) {
        return "main/coronavirus";
    }

    @GetMapping("/corona_monitor")
    public String coronaMonitor(Model model) {
        List<CountryInfo> elems = mainService.coronaMonitor();
        model.addAttribute("elems", elems);
        return "main/corona_monitor";
    }

    @GetMapping("/corona_monitor/get_table")
    public String coronaMonitorGetTable(Model model) {
        List<String> elems = new ArrayList<>();
        model.addAttribute("elems", elems);
        return "main/corona_monitor";
    }


}
