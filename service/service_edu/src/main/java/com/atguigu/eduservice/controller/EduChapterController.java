package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.servicebase.exceptionhandler.ChenException;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
@Api(tags = "章节管理")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //课程大纲，根据课程id进行查询
    @GetMapping("getChapterVideo")
    public Result getChapterVideo(String courseId){
        List<ChapterVo> list = eduChapterService.getChapterVideo(courseId);
        return Result.ok().data("allChapterVideo",list);
    }
    //根据id查询章节
    @GetMapping("getChapter")
    public Result getChapter(String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return Result.ok().data("chapter",eduChapter);
    }

    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter chapter){
        boolean result = eduChapterService.save(chapter);
        return Result.ok();
    }

    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter chapter){
        boolean result = eduChapterService.updateById(chapter);
        return Result.ok();
    }

    @DeleteMapping("deleteChapter")
    public Result deleteChapter(String id){
        eduChapterService.deleteChapter(id);
        return Result.ok();
    }
}

