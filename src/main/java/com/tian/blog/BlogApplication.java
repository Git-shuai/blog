package com.tian.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tian
 * @date 2020/8/12
 */
@SpringBootApplication
@MapperScan("com.tian.blog.mapper")
public class BlogApplication {
     public static void main(String[] args) {
             SpringApplication.run(BlogApplication.class,args);
         }
}
