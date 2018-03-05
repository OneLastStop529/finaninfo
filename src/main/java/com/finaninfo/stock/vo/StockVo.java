package com.finaninfo.stock.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockVo {
    private String sCode;
    private String sStockName;
    private String sCurrentPrice;
    private String sYesterdayPrice;
    private String sRangePrice;
    private String sMainBusiness;
    private String sIndustry;
    private String sPeDynamic;
    private String sPeStatic;
    private String sPb;
    private String sTotalValue;
    private String sRoe;
    private String sDividendRate;
    private String sDividendYear;
}
