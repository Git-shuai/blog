package com.tian.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tian
 * @date 2020/8/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsInfo {
    private String Content;
    private String ErrCode;
    private String OutId;
    private String PhoneNum;
    private String ReceiveDate;
    private String SendDate;
    private Long SendStatus;
}
