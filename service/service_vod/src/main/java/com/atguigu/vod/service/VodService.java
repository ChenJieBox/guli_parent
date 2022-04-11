package com.atguigu.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface VodService {
    public String uploadVideoAly(MultipartFile file);
}
