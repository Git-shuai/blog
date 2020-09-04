package com.tian.blog.controller;


import com.tian.blog.util.OSSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tian
 * @date 2020/7/2
 */
@Controller
public class FileController {

    @Autowired
    private OSSFile ossFile;

    @ResponseBody
    @PostMapping("/file/upload")
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

}
