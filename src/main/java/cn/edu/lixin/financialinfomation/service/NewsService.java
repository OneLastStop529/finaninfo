package cn.edu.lixin.financialinfomation.service;

import cn.edu.lixin.financialinfomation.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {
    Page<News> getNewsByPage(Pageable pageable);
    News findNewsById(Long id);
    Iterable<News> getAllNews();

}
