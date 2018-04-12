package com.yuan.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuan.Dao.UserDao;
import com.yuan.emtity.User;
import com.yuan.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource
	private UserDao userDao;
	
	public User getUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	public ArrayList<User> getAllUser(int pageStart,int limit) {
		return userDao.selectAllUser(pageStart,limit);
	}

	public int addUser(User user) {
		return userDao.insertSelective(user);
	}

	public int deleteUser(ArrayList<String> ids) {
		return userDao.deleteByPrimaryKey(ids);
	}

	public int updateByPrimaryKeySelective(User user) {
		return userDao.updateByPrimaryKeySelective(user);
	}

	public int batchInsert(ArrayList<User> userList) {
		return userDao.batchInsert(userList);
	}

	public int countUser() {
		return userDao.countUser();
	}

}
