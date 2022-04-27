package com.atguigu.educms.service;

import com.atguigu.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author cheJieBox
 * @since 2022-04-24
 */
@Service
public interface CrmBannerService extends IService<CrmBanner> {

    IPage<CrmBanner> getPage(Integer pageNum, Integer size);

    List<CrmBanner> selectAll();
}
