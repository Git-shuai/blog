package com.tian.blog.controller;

import com.alibaba.fastjson.JSON;
import com.tian.blog.bean.User;
import com.tian.blog.dto.Result;
import com.tian.blog.dto.UserPhone;
import com.tian.blog.enums.ResponseCode;
import com.tian.blog.service.UserService;
import com.tian.blog.util.AlibabaSms;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tian
 * @date 2020/8/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private AlibabaSms alibabaSms;


    @PostMapping("/add")
    public String addUser(@RequestBody User user) {

        User user1 = userService.queryUserIsExistByName(user.getUsername());
        if (!StringUtils.isEmpty(user1)) {
            return JSON.toJSONString(new Result<User>(ResponseCode.ERROR_INSERT_CODE, null));
        }

        User result = userService.insertUser(user);
        if (result == null) {
            return JSON.toJSONString(new Result<>(ResponseCode.ERROR_QUERY_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_INSERT_CODE, result));
    }

    @GetMapping("/listAll")
    public String selectUserList() {
        List<User> result = userService.queryUserList();
        if (result.size() == 0 || result == null) {
            JSON.toJSONString(new Result<>(ResponseCode.ERROR_QUERY_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result));
    }

    @GetMapping("/get/{id}")
    public String selectUserById(@PathVariable("id") Long id) {
        User user = userService.queryUserById(id);
        if (user == null) {
            JSON.toJSONString(new Result<>(ResponseCode.ERROR_QUERY_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_QUERY_CODE, user));
    }

    @GetMapping("/like/{name}")
    public String queryUserByLike(@PathVariable("name") String name) {
        List<User> result = userService.queryUserByLike(name);
        if (result.isEmpty()) {
            JSON.toJSONString(new Result<>(ResponseCode.ERROR_QUERY_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result));
    }

    @GetMapping("/delete/{id}")
    public String deleteByUserId(@PathVariable("id") Long id) {
        User result = userService.deleteByUserId(id);
        if (result == null) {
            JSON.toJSONString(new Result<>(ResponseCode.ERROR_QUERY_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result));
    }

    @GetMapping("/page/{page}/{size}")
    public String queryUserByExample(@PathVariable("page") Integer page,
                                     @PathVariable("size") Integer size) {
        List<User> result = userService.queryUserByExample(page, size);
        if (result.isEmpty()) {
            JSON.toJSONString(new Result<>(ResponseCode.ERROR_QUERY_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result));
    }

    @PostMapping("/update")
    public String updateUserByExample(@RequestBody User user) {
        int i = userService.updateUserByExample(user);
        if (i == 1) {
            JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_UPDATE_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.ERROR_CODE, null));
    }

    @PostMapping("/login")
    public String queryUserIsExist(User user) {
        User result = userService.queryUserIsExist(user);
        if (result == null) {
            return JSON.toJSONString(new Result<>(ResponseCode.ERROR_LOGIN_CODE, null));
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(result.getUsername(), result.getPassword());
        subject.login(token);

        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result));
    }

    @PostMapping("/phoneLogin")
    public String phoneLogin(@RequestBody UserPhone userPhone) {
        String sendDetails = alibabaSms.querySendDetails(userPhone);
        if ("CODE_ERROR".equals(sendDetails)) {
            return JSON.toJSONString(new Result<>(ResponseCode.ERROR_SMS_CODE, null));
        }
        User user = new User();
        user.setUsername(userPhone.getPhoneNum());
        user.setGmtCreat(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreat());

        User result = userService.queryUserIsExist(user);
        if (result == null) {
            return JSON.toJSONString(new Result<>(ResponseCode.ERROR_LOGIN_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result));
    }

    @PostMapping("/phoneAdd")
    public String loginByPhone(@RequestBody UserPhone userPhone) {
        String sendDetails = alibabaSms.querySendDetails(userPhone);
        if ("CODE_ERROR".equals(sendDetails)) {
            return JSON.toJSONString(new Result<>(ResponseCode.ERROR_SMS_CODE, null));
        }
        User user = new User();
        user.setUsername(userPhone.getPhoneNum());
        user.setGmtCreat(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreat());

        User user1 = userService.queryUserIsExistByName(user.getUsername());
        if (!StringUtils.isEmpty(user1)) {
            return JSON.toJSONString(new Result<User>(ResponseCode.ERROR_INSERT_CODE, null));
        }

        User result = userService.insertUser(user);
        if (result == null) {
            return JSON.toJSONString(new Result<>(ResponseCode.ERROR_QUERY_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_INSERT_CODE, result));
    }


    @GetMapping("/loginSms/{phone}")
    public String alibabaSms(@PathVariable("phone") String phone) {
        String sendSmsStatus = alibabaSms.sendSms(phone);
        if ("OK".equals(sendSmsStatus)) {
            return JSON.toJSONString(new Result<>(ResponseCode.SUCCESS_ALIBABA_CODE, null));
        }
        return JSON.toJSONString(new Result<>(ResponseCode.ERROR_CODE, null));
    }


    @GetMapping("/getVCode")
    public String vCode(){
        int code = (int)((Math.random()*9+1)*100000);
        return String.valueOf(code);
    }

}
