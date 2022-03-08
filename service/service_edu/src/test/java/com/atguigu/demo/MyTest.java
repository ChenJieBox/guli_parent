package com.atguigu.demo;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;

public class MyTest {
    @Test
    public void Function(){
        String path = "E:\\project001\\在线教育--谷粒学院\\项目笔记课件\\day06";		//要遍历的路径
        File file = new File(path);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f:fs){					//遍历File[]数组
            if(!f.isDirectory())		//若非目录(即文件)，则打印
                System.out.println(f);
        }

    }
    @Test
    public void oss(){

    }

}
