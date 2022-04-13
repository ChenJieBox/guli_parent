package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(tags = "课程分类管理")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        //将上传过来的文件
        eduSubjectService.addSubject(file,eduSubjectService);
        return Result.ok();
    }

    @GetMapping("getAllSubject")
    public Result getAllSubject(){
        List<OneSubject> list =  eduSubjectService.getAllOneTwoSubject();
        return Result.ok().data("list",list);
    }
}

