package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.ChenException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-16
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    VodClient vodClient;

    //删除小节，并同时删除在阿里云上的视频
    @Override
    public boolean removeByVideoId(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)) {
            Result r = vodClient.deleteVideo(videoSourceId);
            if(r.getCode()==20001)
                throw new ChenException(20001,"删除视频失败请检查Service_vod服务是否在线");
        }
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }


}
