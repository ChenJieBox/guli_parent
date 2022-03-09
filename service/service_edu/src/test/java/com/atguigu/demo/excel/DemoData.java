package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty
    private Integer sno;
    @ExcelProperty
    private String sname;
}
