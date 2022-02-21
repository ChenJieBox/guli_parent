package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理");
    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody()
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("执行了ArithmeticException异常");
    }
    @ExceptionHandler(ChenException.class)
    @ResponseBody()
    public Result error(ChenException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
