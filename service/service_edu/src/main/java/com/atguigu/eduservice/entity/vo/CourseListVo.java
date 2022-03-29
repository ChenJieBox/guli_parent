package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CourseListVo {
    String id;
    String title;
    String subjectOne;
    String teacherName;
    String lessonNum;
    String price;
    Date gmtCreate;
}
