package com.atguigu.eduservice.lisener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.ChenException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(){}

    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new ChenException(20001,"文件为空");
        }
        //判断一级分类是否重复
        EduSubject one = this.exitOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(one==null){
            //如果一级分类为空则添加
            one = new EduSubject();
            one.setParentId("0");
            one.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(one);
        }
        //取一级分类ID值
        String pid = one.getId();
        //判断二级分类是否重复
        EduSubject two = this.exitTwoSubject(eduSubjectService, subjectData.getOneSubjectName(),pid);
        if(two==null){
            two = new EduSubject();
            two.setParentId(pid);
            two.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(two);
        }

    }

    //判断一级分类不能重复添加
    private EduSubject exitOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    //判断二级分类不能重复添加
    private EduSubject exitTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject Two = eduSubjectService.getOne(wrapper);
        return Two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
