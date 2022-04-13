package com.atguigu.vod.controller;

import com.aliyuncs.exceptions.ClientException;
import com.atguigu.commonutils.Result;
import com.atguigu.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadVideo")
    public Result uploadVideo(@RequestParam MultipartFile file){
        String videoId = vodService.uploadVideoAly(file);
        return Result.ok().data("videoId",videoId);
    }

    //从阿里云删除云端视频
    @DeleteMapping("removeAlyVideo")
    public Result deleteVideo(@RequestParam(value = "videoId") String videoId) throws ClientException {
        vodService.removeVideoAly(videoId);
        return Result.ok();
    }


}
