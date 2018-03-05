package com.finaninfo.stock.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class StockPagingVo {
    private List<StockVo> rows;
    private Long total;
}
