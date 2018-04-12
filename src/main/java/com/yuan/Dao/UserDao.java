package com.yuan.Dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.yuan.emtity.User;

public interface UserDao {
    int deleteByPrimaryKey(ArrayList<String> ids);
    
    int batchInsert(ArrayList<User> userList);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    ArrayList<User> selectAllUser(@Param("pageStart")int pageStart,@Param("limit")int limit);
    
    int countUser();
}