package com.yuan.emtity;
/**
 * 权限信息表
 * @author Administrator
 *
 */
public class AuthInfo {

	private Integer id;
	
	private String authnam;
	
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthnam() {
		return authnam;
	}

	public void setAuthnam(String authnam) {
		this.authnam = authnam;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
