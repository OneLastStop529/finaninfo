package com.finaninfo.contrlloer;


import com.finaninfo.service.StockService;
import com.finaninfo.stock.dto.StockDividendRate;
import com.finaninfo.stock.vo.StockPagingVo;
import com.finaninfo.stock.vo.StockVo;
import com.finaninfo.util.StockCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping("/get/{code}")
    public StockVo getStock(@PathVariable String code){
        return stockService.getStock(code);
    }




    @RequestMapping("/list")
    public StockPagingVo stockList(Integer offset, Integer limit,String code,String sort,String order){
        Page<StockVo> p = null;
        StockPagingVo stockPagingVo = new StockPagingVo();
        p= stockService.stockList(offset == null ? 1:offset/limit+1,
                limit,
                code,
                sort.equals("true")?null:sort,
                order
        );

        stockPagingVo.setRows(p.getContent());
        stockPagingVo.setTotal(p.getTotalElements());
        return stockPagingVo;
    }



    @RequestMapping("/refresh")
    public void  stockRefresh() throws Exception {
        stockService.stockRefresh();
    }

    @RequestMapping("/del")
    public void deleteStock(String[] codes){
        stockService.delStock(codes);
    }


    @RequestMapping("/add/{code}")
    public String addStock(@PathVariable String code) {
        String msg = "success";
        try {
            stockService.saveStock(code);
        } catch (Exception e) {
            msg = e.getMessage();
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/dividendRate/{code}")
    public List<StockDividendRate> dividendRate(@PathVariable String code){
        String msg = "success";
        List<StockDividendRate> list=null;
        try {
            list= StockCrawler.getStockDivdendRate(code);
        } catch (Exception e) {
            msg = e.getMessage();
            e.printStackTrace();
        }
        return list;
    }

}
