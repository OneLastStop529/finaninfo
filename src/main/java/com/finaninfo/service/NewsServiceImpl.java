package com.finaninfo.service;

import com.finaninfo.commands.NewsCommand;
import com.finaninfo.converters.NewsCommandToNews;
import com.finaninfo.converters.NewsToNewsCommand;
import com.finaninfo.model.News;
import com.finaninfo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsCommandToNews newsCommandToNews;
    @Autowired
    private NewsToNewsCommand newsToNewsCommand;

    @Override
    public Page<News> getNewsByPage(Pageable pageable) {
        return  newsRepository.findAll(pageable);
    }

    @Override
    public News findNewsById(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public Iterable<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public NewsCommand findCommandById(Long id) {

        return newsToNewsCommand.convert(newsRepository.findOne(id));
    }

    @Override
    public void deleteNews(News news) {
        newsRepository.delete(news);
    }

    @Override
    public NewsCommand saveNewsCommand(NewsCommand command) {
        News detachedNews = newsCommandToNews.convert(command);
        News savedNews = newsRepository.save(detachedNews);

        return newsToNewsCommand.convert(savedNews);

    }


}
