package com.finaninfo.stock.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDividendRate {
    private String divendYear;
    private String sdDate;
    private String plan;
    private Double percent;

    public StockDividendRate(String divendYear, String sdDate, String plan, Double percent) {
        this.divendYear = divendYear;
        this.sdDate = sdDate;
        this.plan = plan;
        this.percent = percent;
    }
}
