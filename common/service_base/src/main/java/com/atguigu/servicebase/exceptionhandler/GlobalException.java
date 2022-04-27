package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice()
@Slf4j
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

    /*
    * 当检测到抛出该异常后 比如
    * throw new ChenException(xx,xx)
    * 则会执行该方法 并直接将错误的异常信息返回给请求方接口
    * */
    @ExceptionHandler(ChenException.class)
    @ResponseBody
    public Result error(ChenException e){
        e.printStackTrace();
        log.error(e.getMsg());
        return Result.error().message(e.getMsg()).code(e.getCode());
    }
}
