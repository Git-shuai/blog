package com.tian.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author tian
 * @date 2020/8/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhone {
    private String phoneNum;
    private String code;
    private Date dateNow;
}
