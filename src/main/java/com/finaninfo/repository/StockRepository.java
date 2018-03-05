package com.finaninfo.repository;

import com.finaninfo.stock.pojo.StockEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface StockRepository extends PagingAndSortingRepository<StockEntity,String> {
    Page<StockEntity> findAll(Specification<StockEntity> sCode, Pageable pageable);

    @Query("select sCode from StockEntity")
    String[] getAllStockCode();

    @Modifying
    @Query("update  StockEntity a set a.sStockName=:name , a.sCurrentPrice=:price ,a.sYesterdayPrice=:yprice ,a.sRangePrice=:rp where a.sCode=:code ")
    default void updateIPO(@Param("name") String name, @Param("price") BigDecimal price, @Param("yprice") BigDecimal yprice, @Param("rp") BigDecimal rp, @Param("code") String code) {

    }
}
