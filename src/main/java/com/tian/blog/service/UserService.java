package com.tian.blog.service;

import com.tian.blog.bean.User;
import com.tian.blog.dto.UserDTO;

import java.util.List;

/**
 * @author tian
 * @date 2020/8/13
 */
public interface UserService {

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    public UserDTO insertUser(User user);

    /**
     * 根据ID查询User 数据
     * @param id
     * @return
     */
    public UserDTO queryUserById(Long id);

    /**
     * 模糊查询根据姓名
     * @param name
     * @return
     */
    public  List<UserDTO> queryUserByLike(String name);

    /**
     * 查询User 集合
     * @return
     */
    public List<UserDTO> queryUserList();

    /**
     * 条件查询
     * @return
     */
    public List<UserDTO> queryUserByExample(Integer page, Integer size);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public UserDTO deleteByUserId(Long id);

    /**
     * 更新User数据
     * @param user
     * @return
     */
    public int updateUserByExample(User user);

    /**
     * 查询用户是否存在
     * @param user
     * @return
     */
    public User queryUserIsExist(User user);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public User queryUserIsExistByName(String username);
}
