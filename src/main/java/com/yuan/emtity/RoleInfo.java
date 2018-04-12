package com.yuan.emtity;

import java.util.ArrayList;

/**
 * 角色信息表
 * @author Administrator
*/
public class RoleInfo {

	private Integer id;
	
	private String rolename;
	
	private String roleinfos;
	
	private ArrayList<AuthInfo> authinfo;//权限信息

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoleinfos() {
		return roleinfos;
	}

	public void setRoleinfos(String roleinfos) {
		this.roleinfos = roleinfos;
	}

	public ArrayList<AuthInfo> getAuthinfo() {
		return authinfo;
	}

	public void setAuthinfo(ArrayList<AuthInfo> authinfo) {
		this.authinfo = authinfo;
	}
	
	
}
