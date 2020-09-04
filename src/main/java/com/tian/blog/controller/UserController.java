package com.tian.blog.controller;

import com.alibaba.fastjson.JSON;
import com.tian.blog.bean.User;
import com.tian.blog.dto.Result;
import com.tian.blog.dto.UserDTO;
import com.tian.blog.dto.UserPhone;
import com.tian.blog.enums.ResponseCode;
import com.tian.blog.service.FileService;
import com.tian.blog.service.UserService;
import com.tian.blog.util.AlibabaSms;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
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

    @Resource
    private FileService fileService;

    /**
     *
     * @return
     */
    @RequestMapping("/noAuth")
    public String noAuth() {
        return "未经授权不能登录";
    }

    /**
     * 添加用户（注册（普通），手机号，）
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        User exist = userService.queryUserIsExistByName(user.getUsername());
        if (!StringUtils.isEmpty(exist)) {
            return new Result<>(ResponseCode.ERROR_INSERT_CODE, null);
        }
        UserDTO userDTO = userService.insertUser(user);
        if (userDTO == null) {
            return new Result<>(ResponseCode.ERROR_QUERY_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_INSERT_CODE, userDTO);
    }

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/listAll")
    public Result selectUserList() {
        List<UserDTO> result = userService.queryUserList();
        if (result.size() == 0 || result == null) {
            return new Result<>(ResponseCode.ERROR_QUERY_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result);
    }

    /**
     * 查询根据id
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result selectUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.queryUserById(id);
        if (userDTO == null) {
           return new Result<>(ResponseCode.ERROR_QUERY_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_QUERY_CODE, userDTO);
    }

    /**
     * 根据姓名进行模糊查询
     * @param name
     * @return
     */
    @GetMapping("/like/{name}")
    public Result queryUserByLike(@PathVariable("name") String name) {
        List<UserDTO> result = userService.queryUserByLike(name);
        if (result.isEmpty()) {
            return new Result<>(ResponseCode.ERROR_QUERY_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result);
    }

    /**
     *逻辑删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result deleteByUserId(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.deleteByUserId(id);
        if (userDTO == null) {
            return new Result<>(ResponseCode.ERROR_QUERY_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_QUERY_CODE, userDTO);
    }

    /**
     * 进行分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page/{page}/{size}")
    public Result queryUserByExample(@PathVariable("page") Integer page,
                                     @PathVariable("size") Integer size) {
        List<UserDTO> result = userService.queryUserByExample(page, size);
        if (result.isEmpty()) {
            return new Result<>(ResponseCode.ERROR_QUERY_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result);
    }

    /**
     * 更新
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result updateUserByExample(@RequestBody User user) {
        int i = userService.updateUserByExample(user);
        if (i == 1) {
            return new Result<>(ResponseCode.SUCCESS_UPDATE_CODE, null);
        }
        return new Result<>(ResponseCode.ERROR_CODE, null);
    }

    /**
     * 更新头像
     * @param request
     * @return
     */
    @PostMapping("/file/upload")
    public Result fileUpload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("file");
        String ossFilename="";
        try {
             ossFilename = fileService.fileUpload(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user =(User) request.getSession().getAttribute("user");
        user.setUrl(ossFilename);
        int i = userService.updateUserByExample(user);
        if (i==1){
            return new Result(ResponseCode.SUCCESS_FILE_CODE,null);
        }
        return new Result(ResponseCode.ERROR_CODE,null);
    }


    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result queryUserIsExist(@RequestBody User user) {
        User result = userService.queryUserIsExist(user);
        if (result == null) {
            return new Result<>(ResponseCode.ERROR_LOGIN_CODE, null);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(result.getUsername(), result.getPassword());
        subject.login(token);
        return new Result<>(ResponseCode.SUCCESS_LOGIN_CODE, result);
    }

    /**
     * 手机号码登录
     * @param userPhone
     * @return
     */
    @PostMapping("/phoneLogin")
    public Result phoneLogin(@RequestBody UserPhone userPhone) {
        String sendDetails = alibabaSms.querySendDetails(userPhone);
        if ("CODE_ERROR".equals(sendDetails)) {
            return new Result<>(ResponseCode.ERROR_SMS_CODE, null);
        }
        User user = new User();
        user.setUsername(userPhone.getPhoneNum());
        user.setGmtCreat(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreat());

        User result = userService.queryUserIsExist(user);
        if (result == null) {
            return new Result<>(ResponseCode.ERROR_LOGIN_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_QUERY_CODE, result);
    }

    /**
     * 手机注册
     * @param userPhone
     * @return
     */
    @PostMapping("/phoneAdd")
    public Result loginByPhone(@RequestBody UserPhone userPhone) {
        String sendDetails = alibabaSms.querySendDetails(userPhone);
        if ("CODE_ERROR".equals(sendDetails)) {
            return new Result<>(ResponseCode.ERROR_SMS_CODE, null);
        }
        User user = new User();
        user.setUsername(userPhone.getPhoneNum());
        user.setGmtCreat(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreat());

        User user1 = userService.queryUserIsExistByName(user.getUsername());
        if (!StringUtils.isEmpty(user1)) {
            return new Result<User>(ResponseCode.ERROR_INSERT_CODE, null);
        }

        UserDTO result = userService.insertUser(user);
        if (result == null) {
            return new Result<>(ResponseCode.ERROR_QUERY_CODE, null);
        }
        return new Result<>(ResponseCode.SUCCESS_INSERT_CODE, result);
    }

    /**
     * 手机发送验证码的功能
     * @param phone
     * @return
     */
    @GetMapping("/loginSms/{phone}")
    public Result alibabaSms(@PathVariable("phone") String phone) {
        String sendSmsStatus = alibabaSms.sendSms(phone);
        if ("OK".equals(sendSmsStatus)) {
            return new Result<>(ResponseCode.SUCCESS_ALIBABA_CODE, null);
        }
        return new Result<>(ResponseCode.ERROR_CODE, null);
    }


    /**
     * 发送验证码
     * @return
     */
    @GetMapping("/getVCode")
    public String vCode(){
        int code = (int)((Math.random()*9+1)*100000);
        return String.valueOf(code);
    }

}
