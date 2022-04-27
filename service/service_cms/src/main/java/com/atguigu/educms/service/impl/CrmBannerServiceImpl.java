package com.atguigu.educms.service.impl;

import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.mapper.CrmBannerMapper;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author cheJieBox
 * @since 2022-04-24
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public IPage<CrmBanner> getPage(Integer pageNum, Integer size) {
        Page<CrmBanner> pageTeacher = new Page<>(pageNum,size);
        IPage<CrmBanner> iPage = baseMapper.selectPage(pageTeacher,null);
        return iPage;
    }

    @Override
    public List<CrmBanner> selectAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("id");
        //在sql最后拼接上“limit 1”
        queryWrapper.last("limit 3");
        return baseMapper.selectList(queryWrapper);
    }
}
