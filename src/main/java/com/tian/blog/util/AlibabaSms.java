package com.tian.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.tian.blog.dto.SmsInfo;
import com.tian.blog.dto.UserPhone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tian
 * @date 2020/8/18
 */
@Component
public class AlibabaSms {

    @Value("${alibaba.regionId}")
    private String regionId;

    @Value("${alibaba.accessKeyId}")
    private String accessKeyId;

    @Value("${alibaba.secret}")
    private String secret;

    @Value("${alibaba.signName}")
    private String signName;

    @Value("${alibaba.templateCode}")
    private String templateCode;


    /**
     * 发送验证码，并返回发送的验证码
     * @param phoneNumbers
     * @return
     */
    public String sendSms(String phoneNumbers) {
        int code = (int)((Math.random()*9+1)*100000);

        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        String verCode = "{\"code\":\""+code+"\"}";
        request.putQueryParameter("TemplateParam", verCode);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return "OK";
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "NO";
    }

    /**
     * 检查验证码是否正确，是否在时间内
     * @param userPhone
     * @return
     */
    public String querySendDetails(UserPhone userPhone){
        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumber", userPhone.getPhoneNum());
        request.putQueryParameter("SendDate", "20200818");
        request.putQueryParameter("PageSize", "1");
        request.putQueryParameter("CurrentPage", "1");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();

            JSONObject object = JSON.parseObject(data);
            String smsSendDetailDTOs = object.getString("SmsSendDetailDTOs");

            JSONObject object1 = JSON.parseObject(smsSendDetailDTOs);
            String smsSendDetailDTO = object1.getString("SmsSendDetailDTO");

            JSONArray object2 = JSON.parseArray(smsSendDetailDTO);
            SmsInfo smsInfoYun = object2.getObject(0,SmsInfo.class);

            String code = userPhone.getCode();
            String codeYun = smsInfoYun.getContent().substring(12, 18);
            if (!code.equals(codeYun)){
                return "CODE_ERROR";
            }
            return "OK";
        } catch (ServerException e) {
            return "CODE_ERROR";
        } catch (ClientException e) {
            return "CODE_ERROR";
        }
    }
}
