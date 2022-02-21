package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//生产有参的构造方法
@NoArgsConstructor//生产无参的构造方法
public class ChenException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常信息

}
