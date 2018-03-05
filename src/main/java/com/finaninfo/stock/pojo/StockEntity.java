package com.finaninfo.stock.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
public class StockEntity {
    @Id
    @Column(name = "s_code")
    private String sCode;

    @Column(name = "s_stock_name")
    private String sStockName;


    @Column(name = "s_current_price")
    private BigDecimal sCurrentPrice;

    @Column(name = "s_yesterday_price")
    private BigDecimal sYesterdayPrice;

    @Column(name = "s_range_price")
    private BigDecimal sRangePrice;

    @Column(name = "s_main_business")
    private String sMainBusiness;

    @Column(name = "s_industry")
    private String sIndustry;


    @Column(name = "s_pe_dynamic")
    private BigDecimal sPeDynamic;

    @Column(name = "s_pe_static")
    private BigDecimal sPeStatic;

    @Column(name = "s_pb")
    private BigDecimal sPb;

    @Column(name = "s_stock_value")
    private BigDecimal sTotalValue;

    @Column(name = "s_roe")
    private BigDecimal sRoe;


    @Column(name = "s_dividend_rate")
    private BigDecimal sDividendRate;
    @Column(name = "s_dividend_year")
    private Integer sDividendYear;
    @Column(name = "s_version")
    private Timestamp sVersion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(sCode, that.sCode) &&
                Objects.equals(sStockName, that.sStockName) &&
                Objects.equals(sCurrentPrice, that.sCurrentPrice) &&
                Objects.equals(sYesterdayPrice, that.sYesterdayPrice) &&
                Objects.equals(sRangePrice, that.sRangePrice) &&
                Objects.equals(sMainBusiness, that.sMainBusiness) &&
                Objects.equals(sIndustry, that.sIndustry) &&
                Objects.equals(sPeDynamic, that.sPeDynamic) &&
                Objects.equals(sPeStatic, that.sPeStatic) &&
                Objects.equals(sPb, that.sPb) &&
                Objects.equals(sTotalValue, that.sTotalValue) &&
                Objects.equals(sRoe, that.sRoe) &&
                Objects.equals(sDividendRate, that.sDividendRate) &&
                Objects.equals(sDividendYear, that.sDividendYear) &&
                Objects.equals(sVersion, that.sVersion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sCode, sStockName, sCurrentPrice, sYesterdayPrice, sRangePrice, sMainBusiness, sIndustry, sPeDynamic, sPeStatic, sPb, sTotalValue, sRoe, sDividendRate, sDividendYear, sVersion);
    }
}
