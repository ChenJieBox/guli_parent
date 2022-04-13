package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.*;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CourseListVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.ChenException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-16
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1向课程表中添加课程基本信息
        EduCourse edeCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,edeCourse);
        int insert = baseMapper.insert(edeCourse);
        if (insert==0){
            throw new ChenException(20001,"添加课程信息失败");
        }
        //2向课程简介表中添加课程简介功能
        EduCourseDescription eduCourseDescription =new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescription.setId(edeCourse.getId());
        eduCourseDescriptionService.save(eduCourseDescription);
        return edeCourse.getId();
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询EduCourse信息
        EduCourse eduCourse = baseMapper.selectById(courseId);

        //查询EduCourseDescription信息
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public String updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1向课程表中修改课程基本信息
        EduCourse edeCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,edeCourse);
        int update = baseMapper.updateById(edeCourse);
        if(update==0){
            throw new ChenException(20001,"修改课程信息失败");
        }

        //2向课程简介表中修改课程简介功能
        EduCourseDescription eduCourseDescription =new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(edeCourse.getId());
        eduCourseDescriptionService.updateById(eduCourseDescription);
        return edeCourse.getId();
    }

    @Override
    public CoursePublishVo getPublishCourse(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }
    //分页查询自定义SQL
    @Override
    public Map<String,Object> getCourseList(int pageNum, int size) {
        Page<CourseListVo> page = new Page<>(pageNum,size);
        IPage<CourseListVo> mapIPage = baseMapper.getCourseListVo(page);
        List<CourseListVo> list = mapIPage.getRecords();
        long total = mapIPage.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("courseList",list);
        return map;
    }

    @Override
    @Transactional
    public void deleteCourse(String courseId) {
        //操作删除EduCourse
        baseMapper.deleteById(courseId);
        //操作删除EduChapter
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        eduChapterService.remove(queryWrapper);
        //操作删除EduCourseDescription

        QueryWrapper<EduCourseDescription> queryWrapper3 = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        eduCourseDescriptionService.remove(queryWrapper3);
        //查询所有的小节的视频id
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id",courseId);
        List<EduVideo> list = eduVideoService.list(queryWrapper2);
        StringBuffer videoCourseId = new StringBuffer();
        for(EduVideo e:list){
            videoCourseId.append(e.getVideoSourceId()).append(',');
        }
        String result = videoCourseId.substring(0,videoCourseId.length()-1);

        vodClient.deleteVideo(result);
        //操作删除EduVideo
        QueryWrapper<EduVideo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);
        eduVideoService.remove(queryWrapper1);
    }

}
