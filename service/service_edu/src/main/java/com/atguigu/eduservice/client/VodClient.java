package com.atguigu.eduservice.client;

import com.atguigu.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "service-vod",fallback = VodClientImp.class)
public interface VodClient {
    @DeleteMapping("/eduvod/video/removeAlyVideo")
    public Result deleteVideo(@RequestParam("videoId")String videoId);

    @GetMapping("/eduvod/video/returnMapping")
    Result returnMapping(@RequestParam("returnData")String returnData);
}
