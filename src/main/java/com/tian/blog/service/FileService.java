package com.tian.blog.service;

import com.tian.blog.bean.User;

import javax.servlet.http.HttpServletRequest;
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
}
