package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ReadDemo{
    @ExcelProperty
    private Integer sid;

    @ExcelProperty
    private String sname;
}
