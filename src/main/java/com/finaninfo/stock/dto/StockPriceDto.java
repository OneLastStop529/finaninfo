package com.finaninfo.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter

public class StockPriceDto {

    private String sCode;
    private String sStockName;
    private BigDecimal sCurrentPrice;
    private BigDecimal sYesterdayPrice;
    private BigDecimal sRangePrice;

    public StockPriceDto(String sCode, String sStockName, Double sCurrentPrice, Double sYesterdayPrice, Double sRangePrice) {
        this.sCode = sCode;
        this.sStockName = sStockName;
        this.sCurrentPrice = BigDecimal.valueOf(sCurrentPrice);
        this.sYesterdayPrice = BigDecimal.valueOf(sYesterdayPrice);
        this.sRangePrice = BigDecimal.valueOf(sRangePrice);
    }
}
