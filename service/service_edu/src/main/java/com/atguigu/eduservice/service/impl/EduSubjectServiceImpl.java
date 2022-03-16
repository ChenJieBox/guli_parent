package com.atguigu.eduservice.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author cheJieBox
 * @since 2022-03-09
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void addSubject(MultipartFile file,EduSubjectService subjectService) {
        try{
            InputStream in = file.getInputStream();
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
         }catch (Exception e){
            e.printStackTrace();
        }
    }
    //课程分类列表
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //--要查询得到一种结构体 首先在表中查询出所有的一级分类 实体类型为EduSubject
        /*
        * [
        *   {
        *       id:"xxx",
        *       title:"XXX"
        *       children:[
        *            {
        *              id:"xxx",
        *              title:"XXX"
        *            },
        *            {
        *              id:"xxx",
        *              title:"XXX"
        *            },
        *       ]
        *   },
        *   .......
        *   ..........
        *   {
        *       id:"xxx",
        *       title:"XXX"
        *       children:[....]
        *   }
        * ]
        * */
        List<OneSubject> resultList = new ArrayList<>();
        //查询所有的一级分类
        QueryWrapper<EduSubject> oneQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(oneQueryWrapper);

        //查询所有的二级分类
        QueryWrapper<EduSubject> twoQueryWrapper = new QueryWrapper<>();
        twoQueryWrapper.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(twoQueryWrapper);

        //遍历所有的一级分类,类型为EduSubject
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //currentOneSubject为当前遍历的一级分类课程
            EduSubject currentOneSubject = oneSubjectList.get(i);
            /*copyProperties方法将eduSubject对象中的相应属性copy到oneSubject中
            注意如果字段名称不相同不能复制的*/
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(currentOneSubject,oneSubject);
            resultList.add(oneSubject);
            //
            List<TwoSubject> childrenList = new ArrayList<>();

            //遍历所有的二级分类,类型为EduSubject,并将所有相关二级分类添加到childrenList中
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //currentTwoSubject为当前遍历的二级分类课程
                EduSubject currentTwoSubject = twoSubjectList.get(m);
                if(currentTwoSubject.getParentId().equals(currentOneSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(currentTwoSubject,twoSubject);
                    childrenList.add(twoSubject);
                }
            }
            //将一级分类与二级分类相关联
            oneSubject.setChildren(childrenList);

        }

        return resultList;
    }

}
