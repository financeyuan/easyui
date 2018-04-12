package com.yuan.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 处理页面相关类
 * 
 * 
 */
public class PageUtils {

	/**
	 * 获取分页的总页数
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param pageSize
	 *            每页条数
	 * @return 返回总页数
	 */
	public static int getTotalPage(int totalCount, int pageSize) {

		int totalPage = (totalCount / pageSize)
				+ (totalCount % pageSize == 0 ? 0 : 1);
		return totalPage;

	}

	/**
	 * 根据字符集获取list
	 * 
	 * @param str
	 * @return list
	 */
	public static List<String> getListByStr(String str) {
		List<String> strList = new ArrayList<String>();
		if (StringUtils.isNotEmpty(str)) {
			String[] strs = str.split(",");
			if (str.indexOf(",") > 0) {
				strList = Arrays.asList(strs);
			} else {
				strList.add(str);
			}
		}
		return strList;
	}

	/**
	 * 计算分页信息
	 * 
	 * @param pageIndex
	 *            pageIndex
	 * @param pageSize
	 *            pageSize
	 * @return firstResult
	 */
	public static int getFirstResult(int pageIndex, int pageSize) {

		// 分页的起始记录行号
		int firstResult = 0;
		// 首页时
		if (pageIndex == 1) {
			firstResult = 1;
		} else if (pageIndex > 1) {
			firstResult = (pageIndex - 1) * pageSize + 1;
		}
		return firstResult;
	}

	/**
	 * 计算分页信息
	 * 
	 * @param pageIndex
	 *            pageIndex
	 * @param pageSize
	 *            pageSize
	 * @return maxResult
	 */
	public static int getMaxResult(int pageIndex, int pageSize) {

		// 分页的起始记录行号
		int firstResult = 0;
		int maxResult = 0;
		if (pageIndex <= 1) {
			firstResult = 1;
			maxResult = pageSize;
		} else if (pageIndex > 1) {
			firstResult = (pageIndex - 1) * pageSize + 1;
			maxResult = pageSize + firstResult - 1;
		}
		return maxResult;
	}
}
