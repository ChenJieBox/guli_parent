package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
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
@Api(tags = "课程小节/视频管理")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    VodClient vodClient;

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
        System.out.println(id);
        boolean remove = eduVideoService.removeByVideoId(id);
        return Result.ok();
    }

    @PostMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo video){
        boolean save = eduVideoService.updateById(video);
        return Result.ok();
    }


}

