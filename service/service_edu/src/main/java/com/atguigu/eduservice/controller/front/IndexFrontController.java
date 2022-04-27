package com.atguigu.eduservice.controller.front;


import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
@Api(tags = "前台页面显示")
public class IndexFrontController {
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    EduTeacherService eduTeacherService;

    //查询前八条热门课程，查询前四条名师
    @GetMapping("getLimitCourseTeacher")
    public Result getLimitCourseTeacher(){
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 4");
        List<EduCourse> listCourse = eduCourseService.list(queryWrapper);

        QueryWrapper<EduTeacher> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.orderByDesc("id","sort","gmt_modified");
        queryWrapper2.last("limit 4");
        List<EduTeacher> listTeacher = eduTeacherService.list(queryWrapper2);
        return Result.ok().data("listCourse",listCourse).data("listTeacher",listTeacher);
    }

}
