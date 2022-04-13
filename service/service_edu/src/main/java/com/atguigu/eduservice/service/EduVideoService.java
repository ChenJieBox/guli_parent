package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-16
 */
@Service
public interface EduVideoService extends IService<EduVideo> {

    boolean removeByVideoId(String id);
}
