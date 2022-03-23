package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",courseId);
    }

    @GetMapping("getCourseInfo")
    public Result getCourseInfo(String courseId){
        CourseInfoVo courseInfoVo  = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",courseId);
    }
}

