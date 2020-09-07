package com.tian.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author tian
 * @date 2020/9/4
 */
public interface FileService {

    /**
     * 更新头像
     * @param inputStream
     * @param filename
     * @return
     */
    String fileUpload(InputStream inputStream, String filename);

    /**
     * 表单上传（弃用）
     * @param filename
     * @return
     */
    String bigFileUpload(String filename);

    /**
     * 本地上传
     * @param multipartFile
     * @return
     */
    public String imageUpload(MultipartFile multipartFile);
}
