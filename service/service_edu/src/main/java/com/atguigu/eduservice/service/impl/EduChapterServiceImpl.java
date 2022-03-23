package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.ChenException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;


    //根据课程id查询课程大纲
    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        //用于存储大纲
        List<ChapterVo> result = new ArrayList<>();

        //查询章节
        QueryWrapper<EduChapter> queryWrapperChapter = new QueryWrapper<>();
        queryWrapperChapter.eq("course_id",courseId);
        queryWrapperChapter.orderByAsc("sort");
        List<EduChapter> chapterList = baseMapper.selectList(queryWrapperChapter);

        //查询小节
        QueryWrapper<EduVideo> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id",courseId);
        queryWrapperVideo.orderByAsc("sort");
        List<EduVideo> videoList = eduVideoService.list(queryWrapperVideo);

        //遍历章节并将其添加进大纲
        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter currentEduChapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(currentEduChapter,chapterVo);
            result.add(chapterVo);

            List<VideoVo> children = new ArrayList<>();
            for (int j = 0; j <videoList.size(); j++) {
                EduVideo currentEduVideo = videoList.get(j);
                if(currentEduChapter.getId().equals(currentEduVideo.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(currentEduVideo,videoVo);
                    children.add(videoVo);
                }
            }
            chapterVo.setChildren(children);
        }
        return result;
    }
    //删除章节的方法
    @Override
    public void deleteChapter(String id) {
        //根据章节id查询其是否有小节
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("chapter_id",id);
        int count = eduVideoService.count(queryWrapper);
        if(count>0){//如果能查到则不进行删除
            throw new ChenException(20001,"不能删除");
        }else{//如果找不到则说明无小节,则删除章节
            int result = baseMapper.deleteById(id);
        }
    }

}
