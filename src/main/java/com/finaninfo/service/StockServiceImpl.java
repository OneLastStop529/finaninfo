package com.finaninfo.service;

import com.finaninfo.converters.StockEntityToStockVo;
import com.finaninfo.repository.StockRepository;
import com.finaninfo.stock.dto.StockDividendRate;
import com.finaninfo.stock.dto.StockDto;
import com.finaninfo.stock.dto.StockPriceDto;
import com.finaninfo.stock.pojo.StockEntity;
import com.finaninfo.stock.vo.StockVo;
import com.finaninfo.util.StockCrawler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepository stockRepository;

    @Override
    public StockVo getStock(String code){

        StockEntity stockEntity = stockRepository.findOne(code);
        if (stockEntity == null) {
            StockVo stockVo = new StockVo();
            BeanUtils.copyProperties(stockEntity,stockVo);
            return stockVo;
        }
        return null;
    }

    @Override
    public void saveStock(String code) throws Exception {
        StockEntity stock = stockRepository.findOne(code);
        if (stock == null) {
            stock = new StockEntity();
        }

        List<StockPriceDto> list = StockCrawler.getStockPrice(new String[]{code});
        if (list == null || list.size() == 0) {
            throw new Exception("code doesn't exist");
        }
        StockDto stockDto = StockCrawler.getStockInfo(code);
        StockCrawler.getStockDivdendRate(code);
        List<StockDividendRate> divdendRates = StockCrawler.getStockDivdendRate(code);

        stock.setSDividendYear(-1);
        stock.setSDividendRate(BigDecimal.valueOf(-1));

        if (divdendRates != null) {
            for (int i = 0; i < divdendRates.size(); i++) {
                StockDividendRate item = divdendRates.get(1);
                if (item.getPercent() != -1) {
                    stock.setSDividendYear(Integer.valueOf(item.getDivendYear()));
                    stock.setSDividendRate(BigDecimal.valueOf(item.getPercent()));
                    break;
                }
            }
        }
        stock.setSCode(list.get(0).getSCode());
        stock.setSStockName(list.get(0).getSStockName());
        stock.setSCurrentPrice(list.get(0).getSCurrentPrice());
        stock.setSYesterdayPrice(list.get(0).getSYesterdayPrice());
        stock.setSRangePrice(list.get(0).getSRangePrice());
        stock.setSMainBusiness(stockDto.getSMainBusiness());
        stock.setSIndustry(stockDto.getSIndustry());
        stock.setSPeDynamic(stockDto.getSPeDynamic());
        stock.setSPeStatic(stockDto.getSPeStatic());
        stock.setSPb(stockDto.getSPb());
        stock.setSTotalValue(stockDto.getSTotalValue());
        stock.setSRoe(stockDto.getSRoe());
        stockRepository.save(stock);

    }
    @Override
    public Page<StockVo> stockList(int pageindex, int pagesize, String code, String orderfidld, String sort) {
        Pageable pageable;
        if (orderfidld != null && !orderfidld.isEmpty()&& sort != null) {
            Sort s = new Sort(sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orderfidld);
            pageable = new PageRequest(pageindex - 1, pagesize, s);
        }else{
            Sort s = new Sort(Sort.Direction.DESC, "sCode");
            pageable = new PageRequest(pageindex - 1, pagesize, s);
        }
        Page<StockVo> data = stockRepository.findAll(new Specification<StockEntity>() {
            @Override
            public Predicate toPredicate(Root<StockEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                     ArrayList<Predicate> predicates = new ArrayList<>();
                     if (!StringUtils.isEmpty(code))
                        predicates.add(criteriaBuilder.like(root.<String>get("sCode"), code + "%"));
                     if (predicates.size() == 0)
                        return null;

                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
                }
            }, pageable
            ).map(new StockEntityToStockVo());

            return data;

        }

    @Override
    public void stockRefresh() throws Exception {
        String[] codes = stockRepository.getAllStockCode();
        List<StockPriceDto> stocks = StockCrawler.getStockPrice(codes);
        for (StockPriceDto dto : stocks) {
            StockDto smd = StockCrawler.getStockInfo(dto.getSCode());
            StockEntity stock = stockRepository.findOne(dto.getSCode());
            stock.setSCode(dto.getSCode());
            stock.setSStockName(dto.getSStockName());
            stock.setSCurrentPrice(dto.getSCurrentPrice());
            stock.setSYesterdayPrice(dto.getSYesterdayPrice());
            stock.setSRangePrice(dto.getSRangePrice());
            stock.setSMainBusiness(smd.getSMainBusiness());
            stock.setSIndustry(smd.getSIndustry());
            stock.setSPeDynamic(smd.getSPeDynamic());
            stock.setSPeStatic(smd.getSPeStatic());
            stock.setSPb(smd.getSPb());
            stock.setSTotalValue(smd.getSTotalValue());
            stock.setSRoe(smd.getSRoe());
            stockRepository.save(stock);
        }
    }

    @Override
    @Transactional
    public void updateStockPriceBySina() throws Exception {
        String[] stockCodes = stockRepository.getAllStockCode();
        List<StockPriceDto> stocks = StockCrawler.getStockPrice(stockCodes);
        for (StockPriceDto dto : stocks){
            stockRepository.updateIPO(
                    dto.getSStockName(),
                    dto.getSCurrentPrice(),
                    dto.getSYesterdayPrice(),
                    dto.getSRangePrice(),
                    dto.getSCode()
            );
        }
    }

    @Override
    public void delStock(String[] codes) {
        for (String code : codes) {
            stockRepository.delete(code);
        }
    }


}

