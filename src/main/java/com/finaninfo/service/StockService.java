package com.finaninfo.service;

import com.finaninfo.stock.vo.StockVo;
import org.springframework.data.domain.Page;

public interface StockService {
    StockVo getStock(String code);
    void saveStock(String code) throws Exception;
    Page<StockVo> stockList(int pageindex, int pagesize, final String code, String orderfidld, String sort);
    void stockRefresh() throws Exception;
    void updateStockPriceBySina() throws Exception;
    void delStock(String[] codes);

}
