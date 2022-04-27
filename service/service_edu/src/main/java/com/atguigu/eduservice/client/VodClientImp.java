package com.atguigu.eduservice.client;

import com.atguigu.commonutils.Result;
import org.springframework.stereotype.Component;

@Component
public class VodClientImp implements VodClient{
    @Override
    public Result deleteVideo(String videoId) {
        return Result.error().message("time out");
    }

    @Override
    public Result returnMapping(String returnData) {
        return Result.error().message("time out");
    }
}
