package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sun.xml.internal.ws.util.xml.CDATA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ReadDemo> {
    List<ReadDemo> list = new ArrayList<>();
    @Override
    public void invoke(ReadDemo readDemo, AnalysisContext analysisContext) {
        System.out.println("******"+ readDemo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }
}
