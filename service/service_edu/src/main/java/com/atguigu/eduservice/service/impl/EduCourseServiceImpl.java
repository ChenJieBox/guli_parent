package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exceptionhandler.ChenException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
