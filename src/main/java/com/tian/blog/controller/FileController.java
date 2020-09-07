package com.tian.blog.controller;


import com.tian.blog.service.FileService;
import com.tian.blog.util.OSSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author tian
 * @date 2020/7/2
 */
@Controller
public class FileController {

    @Autowired
    private OSSFile ossFile;

    @Autowired
    private FileService fileService;

    /**
     * 上传到云端
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/OssUpload")
    public String upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("file");
        String oss="";
        try {
            oss = ossFile.oss(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oss;
    }

    /**
     * 上传到本地
     * @param request
     * @return
     */
    @RequestMapping("/baseUpload")
    @ResponseBody
    public String savePic(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("file");
        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "")+file.getOriginalFilename();
        //设置图片上传路径
        String path = System.getProperty("user.dir"); //获取项目路径
        String url = "\\src\\main\\resources\\static\\upload\\";   //上传文件保存路径
        File file1 = new File(path+url);
        if (!file1.exists()){
            file1.mkdir();
        }
        String basePath=path+url+name;
        //以绝对路径保存重名命后的图片

        try {
            file.transferTo(new File(basePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePath;
    }

}
