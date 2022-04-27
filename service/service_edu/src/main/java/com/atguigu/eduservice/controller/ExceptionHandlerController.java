package com.atguigu.eduservice.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.atguigu.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
@CrossOrigin
public class ExceptionHandlerController {

    /*
    *@ExceptionHandler注解中可以添加参数，
    *参数是某个异常类的class，代表这个方法专门处理该类异常，比如这样：
    */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionRun(Exception exception) {
        exception.printStackTrace();
        String message = "此类中发生了控制正异常，请在此处理";
        System.out.println(message);
        return message;
    }

    @GetMapping("nullPointerExceptionRun")
    public Result test() {
        String s=null;
        System.out.println(s.toString());
        return Result.ok();
    }
}
