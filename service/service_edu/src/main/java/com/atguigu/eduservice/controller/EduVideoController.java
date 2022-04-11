package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @GetMapping("getVideo")
    public Result getVideo(String id){
        EduVideo video = eduVideoService.getById(id);
        return Result.ok().data("video",video);
    }

    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @DeleteMapping("deleteVideo")
    public Result deleteVideo(String id){
        boolean remove = eduVideoService.removeById(id);
        return Result.ok();
    }

    @PostMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo video){
        boolean save = eduVideoService.updateById(video);
        System.out.println(video.toString());
        return Result.ok();
    }
}

