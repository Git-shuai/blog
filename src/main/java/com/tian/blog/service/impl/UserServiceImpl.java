package com.tian.blog.service.impl;

import com.tian.blog.bean.User;
import com.tian.blog.bean.UserExample;
import com.tian.blog.mapper.UserExtMapper;
import com.tian.blog.mapper.UserMapper;
import com.tian.blog.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public User insertUser(User user) {
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
            return user;
        }
    }

    /**
     * 根据ID查询User 数据
     *
     * @param id
     * @return
     */
    @Override
    public User queryUserById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> queryUserByLike(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameLike("%" + name + "%");
        List<User> userList = userMapper.selectByExample(example);
        if (userList != null && userList.size() != 0) {
            return userList;
        }
        return null;
    }

    /**
     * 查询User 集合
     *
     * @return
     */
    @Override
    public List<User> queryUserList() {
        
        List<User> users = userMapper.selectByExample(new UserExample());
        if (users.size() != 0 || users != null) {
            return users;
        } else {
            return null;
        }
    }

    @Override
    public List<User> queryUserByExample(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<User> users = userExtMapper.queryUserListByLimit(size, offset);
        if (users.size() == 0 && users == null) {
            return null;
        }
        return users;
    }

    @Transactional
    @Override
    public User deleteByUserId(Long id) {
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
        return user;
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
