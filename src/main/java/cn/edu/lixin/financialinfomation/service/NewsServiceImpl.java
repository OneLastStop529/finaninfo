package cn.edu.lixin.financialinfomation.service;

import cn.edu.lixin.financialinfomation.model.News;
import cn.edu.lixin.financialinfomation.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

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
}
