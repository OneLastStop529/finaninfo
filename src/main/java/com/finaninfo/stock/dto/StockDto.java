package com.finaninfo.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class StockDto {
    private String sCode;
    private String sStockName;
    private BigDecimal sCurrentPrice;
    private BigDecimal sYesterdayPrice;
    private BigDecimal sRangePrice;
    private String sMainBusiness;
    private String sIndustry;
    private BigDecimal sPeDynamic;
    private BigDecimal sPeStatic;
    private BigDecimal sPb;
    private BigDecimal sTotalValue;
    private BigDecimal sRoe;
    private BigDecimal sDividendRate;
    private Integer sDividendYear;
}
