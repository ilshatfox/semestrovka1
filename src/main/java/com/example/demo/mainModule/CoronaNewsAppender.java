package com.example.demo.mainModule;

import com.example.demo.models.Event;
import com.example.demo.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.mainModule.SendRequest.send;

@Component
public class CoronaNewsAppender {

    @Autowired
    private NewsRepository newsRepository;

    @Scheduled( initialDelay = 60 * 1000, fixedDelay = 60 * 1000)
    public void createOrUpdateNews() {
        List<Event> events = getNews();
        events = getActualNews(events);
        events = updateNewsUrls(events);
        ArrayList<Event> events2 = new ArrayList<>();
        for (int i = events.size() - 1; i >= 0; i--) {
            events2.add(events.get(i));
        }
        newsRepository.saveAll(events2);
    }

    private List<Event> getNews() {
        String req = send("https://coronavirus-monitor.ru/ru/novosti");
        String[] texts = req.split("<p>");
        ArrayList<String> arrTexts = new ArrayList<>();
        for (int i = 1; i < texts.length; i++) {
            arrTexts.add(texts[i].split("</p>")[0]);
        }
        String[] texts2 = req.split("<a href=\"/ru/novosti/");
        ArrayList<String> arrNames = new ArrayList<>();
        ArrayList<String> arrUrls = new ArrayList<>();

        for (int i = 1; i < texts2.length; i++) {
            String[] t1 = texts2[i].split("\">");
            String url = "https://coronavirus-monitor.ru/ru/novosti/" + t1[0];
            String name = t1[1].split("<")[0];
            arrNames.add(name);
            arrUrls.add(url);
        }
        String[] texts3 = req.split("https://file.coronavirus-monitor.com/");
        ArrayList<String> arrPics = new ArrayList<>();
        for (int i = 1; i < texts3.length; i++) {
            String picUrl = "https://file.coronavirus-monitor.com/" + texts3[i].split("\"")[0];
            arrPics.add(picUrl);
        }
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 0; i < arrTexts.size(); i++) {
            Event event1 = Event.builder()
                    .name(arrNames.get(i))
                    .text(arrTexts.get(i))
                    .originalUrl(arrUrls.get(i))
                    .fileUrl(arrPics.get(i)).build();
            events.add(event1);
        }
        return events;
    }

    private List<Event> getActualNews(List<Event> allNews) {
//        Самый первый это самый новые элемент
        Optional<Event> optionalNews = newsRepository.findTopByOrderByIdDesc();
        Integer index = allNews.size();
        System.out.println("optionalNews" + optionalNews);
        if (optionalNews.isPresent()) {
            Event event = optionalNews.get();
            for (int i = 0; i < allNews.size(); i++) {
                if (allNews.get(i).getName().equals(event.getName())) {
                    index = i;
                    break;
                };
            }

        }
        ArrayList<Event> targNews = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            targNews.add(allNews.get(i));
        }
        return targNews;
    }

    private List<Event> updateNewsUrls(List<Event> events) {
        for (int i = 0; i < events.size(); i++) {
            events.set(i, updateNewUrl(events.get(i)));
        }
        return events;
    }
//
    private Event updateNewUrl(Event event) {
        String req = send(event.getOriginalUrl());
        String url = req.split("Источник: <a href=\"")[1].split("\"")[0];
        event.setOriginalUrl(url);
        return event;
    }
}
