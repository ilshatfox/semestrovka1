package com.example.demo.service;

import com.example.demo.models.Event;

import java.util.List;

public interface NewsService {
    public List<Event> coronaNews(String pageNum);
}
