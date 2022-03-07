package com.atguigu.oss.controller;

import com.atguigu.commonutils.Result;

import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileos")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像方法
    @PostMapping
    public Result uploadOssFile(MultipartFile file){
        //获取上传文件
        //方法返回上传到OSS的路径，将路径返回
       String url = ossService.uploadFileAvatar(file);
       return Result.ok().data("url",url);
    }
}
