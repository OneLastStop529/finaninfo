package com.finaninfo.converters;

import com.finaninfo.stock.pojo.StockEntity;
import com.finaninfo.stock.vo.StockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class StockEntityToStockVo implements Converter<StockEntity,StockVo> {
    @Override
    public StockVo convert(StockEntity stockEntity) {
        StockVo stockVo = new StockVo();
        BeanUtils.copyProperties(stockEntity,stockVo);
        stockVo.setSCurrentPrice(stockEntity.getSCurrentPrice().toString());
        stockVo.setSYesterdayPrice(stockEntity.getSYesterdayPrice().toString());
        stockVo.setSRangePrice(String.format("%s%s", stockEntity.getSRangePrice(),"%"));

        String ped = stockEntity.getSPeDynamic().intValue()==-1?"--":stockEntity.getSPeDynamic().toString();
        stockVo.setSPeDynamic(ped);
        String pes = stockEntity.getSPeStatic().intValue()==-1?"--":stockEntity.getSPeStatic().toString();
        stockVo.setSPeStatic(pes);
        String pb =stockEntity.getSPb().intValue()==-1?"--":stockEntity.getSPb().toString();
        stockVo.setSPb(pb);
        String roe = stockEntity.getSRoe().intValue()==-1?"--":stockEntity.getSRoe().toString();
        stockVo.setSRoe(roe);
        String totalValue = stockEntity.getSTotalValue().intValue()==-1?"--":stockEntity.getSTotalValue().toString();
        stockVo.setSTotalValue(totalValue);
        String divendYear = stockEntity.getSDividendYear().intValue()==-1?"--":stockEntity.getSDividendYear().toString();
        stockVo.setSDividendYear(divendYear);
        String divendRate = stockEntity.getSDividendRate().intValue()==-1?"--":stockEntity.getSDividendRate().toString();
        stockVo.setSDividendYear(divendRate);

        return stockVo;
    }
}
