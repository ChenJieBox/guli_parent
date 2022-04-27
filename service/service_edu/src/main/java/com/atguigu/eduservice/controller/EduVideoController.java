package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.ChenException;
import com.baomidou.mybatisplus.extension.api.R;
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
        boolean remove = eduVideoService.removeByVideoId(id);
        return Result.ok();
    }

    @PostMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo video){
        boolean save = eduVideoService.updateById(video);
        return Result.ok();
    }

    @GetMapping("test")
    public Result test(){
        Result r = vodClient.returnMapping("返回值");
        if(r.getCode()==20001){
            System.out.println("Exception");
            throw new ChenException(20001,"调用Service_vod的returnMapping接口失败，请检查service_vod的服务是否在线");
        }
        return r;
    }
}

