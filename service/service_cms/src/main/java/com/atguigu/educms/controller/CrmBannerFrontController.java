package com.atguigu.educms.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/educms/crmfrontbanner")
@CrossOrigin
@Api(tags = "轮播图用户管理")
public class CrmBannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getBannerAll")
    public Result getBannerAll(){
        List<CrmBanner> list = crmBannerService.selectAll();
        return Result.ok().data("list",list);
    }
}
