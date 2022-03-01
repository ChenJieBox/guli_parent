package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(tags = "教师登录管理")
@RequestMapping("/eduService/user")
public class EduLoginController {
    //login
    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public Result info(){
        return Result.ok().data("roles","[admin]").data("name","admin");
    }
}
