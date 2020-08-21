package com.tian.blog.mapper;

import com.tian.blog.bean.User;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.PathParam;
import java.util.List;

public interface UserExtMapper {

    @Select("select ID, USERNAME, GMT_CREAT, GMT_MODIFIED, PHONE, EMAIL, QQ, WECHAT, DELETE, VERSION\n" +
            "FROM USER limit #{size} offset #{offset};")
    List<User> queryUserListByLimit(@PathParam("size") Integer size,@PathParam("offset") Integer offset);
}