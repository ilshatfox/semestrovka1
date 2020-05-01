package com.example.demo.service;

import com.example.demo.models.Event;
import com.example.demo.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsRepository newsRepository;

    public List<Event> coronaNews(String pageNum) {
        if (pageNum == null) {
            pageNum = "0";
        }
        Integer pageId = Integer.parseInt(pageNum);
        List<Event> events = newsRepository.findAll();
        List<Event> targList = new ArrayList<>();
        Integer elemsInPage = 10;
        for (int i = 0; i < elemsInPage; i++) {
            try {
                targList.add(events.get(events.size() - pageId * elemsInPage - i));
            } catch (IndexOutOfBoundsException ex) {

            }

        }
        return targList;
    }

//    public String coronaNewsAdd(Model model, @PathVariable("page_id") String pageNum) {
//        if (pageNum == null) {
//            pageNum = "0";
//        }
//        Integer pageId = Integer.parseInt(pageNum);
//        List<Event> events = newsRepository.findAll();
//        List<Event> targList = new ArrayList<>();
//        Integer elemsInPage = 10;
//        for (int i = 0; i < elemsInPage; i++) {
//            try {
//                targList.add(events.get(events.size() - pageId * elemsInPage - i));
//            } catch (IndexOutOfBoundsException ex) {
//
//            }
//
//        }
//        model.addAttribute("news_list", targList);
//        return "main/corona_news_page_add";
//    }

//    public String coronaNewsById(Model model, @PathVariable("id") String idLine) {
////        News news = News.builder().filename("img.png").name("ndfsdfsdf").text("dfsdfgsdfgsdfgsdfgsdfg").build();
////        newsRepository.save(news);
//        Optional<Event> elem = newsRepository.findById(Long.parseLong(idLine));
//        if (elem.isPresent()) {
//            model.addAttribute("news_path", newsPath);
//            model.addAttribute("elem", elem.get());
//            return "main/corona_news";
//        }
//        return "not_found";
//    }
}
