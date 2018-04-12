package com.yuan.emtity;
/**
 * 用户信息实体类
 * @author Administrator
 *
 */

public class User {
	
    private Integer id;

    private String username;

    private String password;

    private Integer age;
    
    private String sex;
    
    private Integer departmentid;
    
    private String email;
    
    private String address;
    
    private RoleInfo rolein;//角色信息表

	public RoleInfo getRolein() {
		return rolein;
	}

	public void setRolein(RoleInfo rolein) {
		this.rolein = rolein;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    

}