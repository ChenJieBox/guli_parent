package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private String price;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;

}
