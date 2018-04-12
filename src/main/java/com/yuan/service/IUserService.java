package com.yuan.service;

import java.util.ArrayList;

import com.yuan.emtity.User;

public interface IUserService {
	
	public User getUserById(int userId);
	
	public ArrayList<User> getAllUser(int pageStart,int limit);
	
	public int addUser(User user);
	
	public int deleteUser(ArrayList<String> ids);
	
	public int updateByPrimaryKeySelective(User user);
	
	public int batchInsert(ArrayList<User> userList); 
	
	public int countUser();
}
