package com.atguigu.educms.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author chenJieBox
 * @since 2022-04-24
 */
@RestController
@RequestMapping("/educms/crmadminbanner")
@CrossOrigin
@Api(tags = "轮播图admin管理")
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //分页查询banner
    @ApiOperation("分页查询banner")
    @GetMapping("getBannerPage")
    public Result getBannerPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size) {
        if (pageNum.equals(null) || pageNum == 0)
            pageNum = 0;
        if (size.equals(null) || size == 0)
            size = 5;
        Map<String, Object> map = new HashMap<>();
        /*Page<CrmBanner> pageTeacher = new Page<>(pageNum,size);
        IPage<CrmBanner> page = crmBannerService.page(pageTeacher, null);
        List<CrmBanner> record = page.getRecords();

        map.put("list",page.getRecords());
        map.put("total",page.getTotal());
        map.put("page",page.getPages());
        map.put("size",page.getSize());*/
        IPage<CrmBanner> page = crmBannerService.getPage(pageNum, size);
        map.put("list", page.getRecords());
        map.put("total", page.getTotal());
        map.put("page", page.getPages());
        map.put("size", page.getSize());
        return Result.ok().data(map);
    }

    //查询banner
    @ApiOperation("查询banner")
    @GetMapping("getBannerById")
    public Result getBannerById(@RequestParam("bannerId") String bannerId) {
        CrmBanner crmBanner = crmBannerService.getById(bannerId);
        return Result.ok().data("crmBanner", crmBanner);
    }

    //添加banner
    @ApiOperation("添加banner")
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return Result.ok();
    }

    //删除banner
    @ApiOperation("删除banner")
    @PostMapping("deleteBanner")
    public Result deleteBanner(@RequestParam("bannerId") String bannerId) {
        crmBannerService.removeById(bannerId);
        return Result.ok();
    }

    //修改banner
    @ApiOperation("修改banner")
    @PostMapping("updateBanner")
    public Result updateBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return Result.ok();
    }
}

