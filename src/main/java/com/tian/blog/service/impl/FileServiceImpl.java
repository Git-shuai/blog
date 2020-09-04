package com.tian.blog.service.impl;

import com.tian.blog.bean.User;
import com.tian.blog.service.FileService;
import com.tian.blog.service.UserService;
import com.tian.blog.util.OSSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author tian
 * @date 2020/9/4
 */
@Service("FileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSFile ossFile;


    /**
     *
     * @param inputStream
     * @param filename
     * @return
     */
    @Override
    public String fileUpload(InputStream inputStream, String filename) {
        String ossFilename="";
        ossFilename = ossFile.oss(inputStream,filename);
        return ossFilename;
    }
}
