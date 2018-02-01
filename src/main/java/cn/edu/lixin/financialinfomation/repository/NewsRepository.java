package cn.edu.lixin.financialinfomation.repository;

import cn.edu.lixin.financialinfomation.model.News;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface NewsRepository extends PagingAndSortingRepository<News,Long> {
}
