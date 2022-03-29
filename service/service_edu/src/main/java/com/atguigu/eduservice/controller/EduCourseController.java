package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-16
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    // 添加课程信息 操作两张表 EduCourse+ EduDescription
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",courseId);
    }

    // 获取课程信息 获取两张表 EduCourse+ EduDescription
    @GetMapping("getCourseInfo")
    public Result getCourseInfo(String courseId){
        CourseInfoVo courseInfoVo  = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }

    // 更新课程信息 更新两张表 EduCourse+ EduDescription
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",courseId);
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourse")
    public Result getPublishCourse(String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourse(courseId);
        return Result.ok().data("CoursePublishVo",coursePublishVo);
    }

    //课程最终发布 仅仅修改课程状态即可
    @PostMapping("publishCourse")
    public Result publishCourse(String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus("Normal");
        eduCourse.setId(courseId);
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    //获取所有课程数据 多表查询 EduCourse+ EduTeacher+ EduSubject
    @GetMapping("getCourseList")
    public Result getCourseList(Integer page, Integer size){
        System.out.println(page+"a"+size);
        if (page==null){
            page=0;
        }
        if(size==null){
            size=5;
        }
        System.out.println(page+"a"+size);
        Map<String , Object> map = eduCourseService.getCourseList(page,size);
        return Result.ok().data(map);
    }

    //删除课程信息 多表操作 EduCourse + EduChapter + EduVideo
    @DeleteMapping("deleteCourse")
    public Result deleteCourse(String courseId){
        eduCourseService.deleteCourse(courseId);
        return Result.ok();
    }
}

