package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PresentationDirection;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author cheJieBox
 * @since 2022-02-14
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@Api(tags = "教师管理")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;


    //查询讲师表所有数据
    @ResponseBody
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("item",list);
    }

    //逻辑删除讲师的方法
    @ResponseBody
    @DeleteMapping("delete")
    public Result removeTeacher( String id){
        if(id==null)
            return Result.error();
        boolean flag = eduTeacherService.removeById(id);
        System.out.println(flag);
        return flag? Result.ok(): Result.error();
    }

    //分页查询教师方法
    @ResponseBody
    @GetMapping("pageTeachere/{pageNum}/{size}")
    public Result pageListTeacher(@PathVariable int pageNum,@PathVariable int size){
        Page<EduTeacher> pageTeacher = new Page<>(pageNum,size);
        //调用方法分页查询
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows", records);
        return Result.ok().data(map);
    }
}

