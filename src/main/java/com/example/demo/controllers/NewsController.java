package com.example.demo.controllers;

import com.example.demo.models.Event;
import com.example.demo.repositories.NewsRepository;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Value("${pictures.dir.news}")
    private String newsPath;

    @GetMapping("/corona_news/page/{page_id}")
    public String coronaNews(Model model, @PathVariable("page_id") String pageNum) {
        List<Event> targList = newsService.coronaNews(pageNum);
        model.addAttribute("news_list", targList);
        return "main/corona_news_page";
    }

    @GetMapping("/corona_news/page_add/{page_id}")
    public String coronaNewsAdd(Model model, @PathVariable("page_id") String pageNum) {
        List<Event> targList = newsService.coronaNews(pageNum);
        model.addAttribute("news_list", targList);
        return "main/corona_news_page_add";
    }
}
