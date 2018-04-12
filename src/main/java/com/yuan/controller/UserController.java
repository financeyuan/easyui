package com.yuan.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yuan.emtity.User;
import com.yuan.service.IUserService;
import com.yuan.utils.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;

	@RequestMapping("/showAllUser")
	public ModelAndView getAllUser(HttpServletResponse response,@RequestParam(value="page") String page,@RequestParam(value="rows") String rows) throws Exception {
		int start= (Integer.parseInt(page)-1)*Integer.parseInt(rows);
		ArrayList<User> userList = this.userService.getAllUser(start,Integer.parseInt(rows));
		JSONObject jso=new JSONObject();
		jso.put("rows", JSONArray.fromObject(userList));
		int count = this.userService.countUser();
		jso.put("total",count);
		jso.put("page", count/Integer.parseInt(rows));
		ResponseUtil.write(response, jso);
		return null;
	}
	
	@RequestMapping("/addUser")
	public String addUset(HttpServletResponse response,User user) throws Exception{
		int num = this.userService.addUser(user);
		JSONObject jso=new JSONObject();
		if(num > 0){
			jso.put("success", true);
			ResponseUtil.write(response, jso);
		}else{
			jso.put("success", false);
			ResponseUtil.write(response, jso);
		}
		return null;
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletResponse response,User user) throws Exception{
		int num = this.userService.updateByPrimaryKeySelective(user);
		JSONObject jso=new JSONObject();
		if(num > 0){
			jso.put("success", true);
			ResponseUtil.write(response, jso);
		}else{
			jso.put("success", false);
			ResponseUtil.write(response, jso);
		}
		return null;
	}
	@RequestMapping("/deleteUser")
	public String dele(HttpServletResponse response,@RequestParam(value="ids") String ids) throws Exception{
		String[] idStr = ids.split(",");
		List<String> lit = Arrays.asList(idStr);
		ArrayList<String> lists = new ArrayList<String>(lit);
		int num = this.userService.deleteUser(lists);
		JSONObject jso=new JSONObject();
		if(num > lists.size()-1){
			jso.put("success", true);
			ResponseUtil.write(response, jso);
		}else{
			jso.put("success", false);
			ResponseUtil.write(response, jso);
		}
		return null;
	}
}
