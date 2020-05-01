package com.example.demo.controllers;

import com.example.demo.models.News;
import com.example.demo.repositories.NewsRepository;
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
    private NewsRepository newsRepository;

    @Value("${pictures.dir.news}")
    private String newsPath;

    @GetMapping("/corona_news/page/{page_id}")
    public String coronaNews(Model model, @PathVariable("page_id") String pageNum) {
        if (pageNum == null) {
            pageNum = "0";
        }
        Integer pageId = Integer.parseInt(pageNum);
        List<News> news = newsRepository.findAll();
        List<News> targList = new ArrayList<>();
        Integer elemsInPage = 10;
        for (int i = 0; i < elemsInPage; i++) {
            try {
                targList.add(news.get(news.size() - pageId * elemsInPage - i));
            } catch (IndexOutOfBoundsException ex) {

            }

        }
        model.addAttribute("news_list", targList);
        return "main/corona_news_page";
    }

    @GetMapping("/corona_news/{id}")
    public String coronaNewsById(Model model, @PathVariable("id") String idLine) {
//        News news = News.builder().filename("img.png").name("ndfsdfsdf").text("dfsdfgsdfgsdfgsdfgsdfg").build();
//        newsRepository.save(news);
        Optional<News> elem = newsRepository.findById(Long.parseLong(idLine));
        if (elem.isPresent()) {
            model.addAttribute("news_path", newsPath);
            model.addAttribute("elem", elem.get());
            return "main/corona_news";
        }
        return "not_found";
    }
}
