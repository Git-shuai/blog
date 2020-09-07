package com.tian.blog.service.impl;

import com.tian.blog.service.FileService;
import com.tian.blog.util.OSSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 大文件上传
     * @param filename
     * @return
     */
    @Override
    public String bigFileUpload(String filename) {
            return ossFile.PostObject(filename);
    }

    @Override
    public String imageUpload(MultipartFile multipartFile)  {

        return "";
    }
}
