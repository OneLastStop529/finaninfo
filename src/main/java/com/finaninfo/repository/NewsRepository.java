package com.finaninfo.repository;

import com.finaninfo.model.News;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface NewsRepository extends PagingAndSortingRepository<News,Long> {
}
