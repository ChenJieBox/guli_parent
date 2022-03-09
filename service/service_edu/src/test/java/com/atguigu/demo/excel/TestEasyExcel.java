package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEasyExcel {

    public static void main(String[] args) {
        //设置写入文件地址和excel文件名称
        //实现excel写的操作
        String fileName = "D:\\demo.xlsx";
        //2调用EasyExcel中的方法实现写操作
        EasyExcel.read(fileName, ReadDemo.class, new ExcelListener()).sheet().doRead();

    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i=0;i<20;i++){
            DemoData data = new DemoData();
            data.setSname("lucy"+i);
            data.setSno(i);
            list.add(data);
        }
        return list;
    }
}
