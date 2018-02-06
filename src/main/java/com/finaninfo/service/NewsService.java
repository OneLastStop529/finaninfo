package com.finaninfo.service;

import com.finaninfo.commands.NewsCommand;
import com.finaninfo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {
    Page<News> getNewsByPage(Pageable pageable);
    News findNewsById(Long id);
    Iterable<News> getAllNews();

    NewsCommand findCommandById(Long id);
}
