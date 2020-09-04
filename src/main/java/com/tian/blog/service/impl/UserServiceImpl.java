package com.tian.blog.service.impl;

import com.tian.blog.bean.User;
import com.tian.blog.bean.UserExample;
import com.tian.blog.dto.UserDTO;
import com.tian.blog.mapper.UserExtMapper;
import com.tian.blog.mapper.UserMapper;
import com.tian.blog.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tian
 * @date 2020/8/13
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserExtMapper userExtMapper;

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public UserDTO insertUser(User user) {
        //盐值
        String salt =new SecureRandomNumberGenerator().nextBytes().toHex();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String newPassword = new Md5Hash(user.getPassword(),salt,3).toString();
        user.setPassword(newPassword);
        user.setSalt(salt);
        int i = userMapper.insertSelective(user);
        if (i != 1) {
            return null;
        } else {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            return userDTO;
        }
    }

    /**
     * 根据ID查询User 数据
     * @param id
     * @return
     */
    @Override
    public UserDTO queryUserById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user.getDelete()==1){
            return null;
        }
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            return userDTO;
        }
        return null;
    }

    @Override
    public List<UserDTO> queryUserByLike(String name) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andDeleteNotEqualTo(1)
                .andUsernameLike("%" + name + "%");
        List<User> userList = userMapper.selectByExample(example);
        if (userList != null && userList.size() != 0) {
            return userList.stream().map(q -> {
                UserDTO dto = new UserDTO();
                BeanUtils.copyProperties(q,dto);
                return dto;
            }).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 查询User 集合
     *
     * @return
     */
    @Override
    public List<UserDTO> queryUserList() {

        UserExample example = new UserExample();
        example.createCriteria()
                .andDeleteNotEqualTo(1);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() != 0 || userList != null) {

            return userList.stream().map(q -> {
                UserDTO dto = new UserDTO();
                BeanUtils.copyProperties(q,dto);
                return dto;
            }).collect(Collectors.toList());

        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> queryUserByExample(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<User> userList = userExtMapper.queryUserListByLimit(size, offset);
        if (userList.size() == 0 && userList == null) {
            return null;
        }
        return userList.stream().map(q -> {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(q,dto);
            return dto;
        }).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public UserDTO deleteByUserId(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        //逻辑删除
        user.setDelete(1);
        int deleteStatus = userMapper.updateByPrimaryKeySelective(user);
        if (deleteStatus != 1) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    @Transactional
    @Override
    public int updateUserByExample(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());
        int i = userMapper.updateByExampleSelective(user, example);
        if (i != 1) {
            return -1;
        }
        return 1;
    }

    @Override
    public User queryUserIsExist(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andUsernameLike(user.getUsername());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User queryUserIsExistByName(String  username) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andUsernameLike(username);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() >0) {
            return users.get(0);
        }
        return null;
    }


}
