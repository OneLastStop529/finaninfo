package com.finaninfo.stock.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class JqGridVo<T> {

    private List<T> rows;// json中代表实际模型数据的入口
    private int total;// json中代表页码总数的数据
    private Long records;// json中代表数据行总数的数据
    private Integer page;// json中代表当前页码的数据

}
