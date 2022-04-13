package com.atguigu.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface VodService {
    public String uploadVideoAly(MultipartFile file);

    void removeVideoAly(String videoId) throws ClientException;
}
