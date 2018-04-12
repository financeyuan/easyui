package com.yuan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yuan.emtity.User;
import com.yuan.service.IUserService;
import com.yuan.utils.ExcelException;
import com.yuan.utils.ExcelUtil;
import com.yuan.utils.ResponseUtil;

@Controller
@RequestMapping("/LeadToExcel")
public class leadToExcelController {
	@Resource
	private IUserService userService;
	@RequestMapping("/LeadToUser")
	public String leadToExcelQuestionBank(HttpServletResponse response,@RequestParam(value="page") String page,@RequestParam(value="rows") String rows) throws Exception {  
		 JSONObject jso=new JSONObject();
		try {  
	        // excel表格的表头，map  
	        LinkedHashMap<String, String> fieldMap = leadToExcelController.getLeadToFiledPublicQuestionBank();  
	        // excel的sheetName  
	        String sheetName = "用户";  
	        // excel要导出的数据  
	        ArrayList<User> list = this.userService.getAllUser((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows));  
	        // 导出  
	        if (list == null || list.size() == 0) {  
	            jso.put("success", false);
				ResponseUtil.write(response, jso);
	        }else {  
	            //将list集合转化为excle  
	            ExcelUtil.listToExcel(list, fieldMap, sheetName, response);  
	        } 
	        return null;
	    } catch (ExcelException e) {  
	        e.printStackTrace(); 
	        jso.put("success", false);
			ResponseUtil.write(response, jso);
	    } 
	    return null;
	} 
	
	@RequestMapping("/LeadInUser")
	public String leadInExcel(HttpServletResponse response,@RequestParam(value="excel")CommonsMultipartFile excel) throws Exception{
		JSONObject jso=new JSONObject();
		InputStream in; 
		try {  
            // 获取前台exce的输入流  
            in = excel.getInputStream();  
            //excel的表头与文字对应，获取excel表头  
            LinkedHashMap<String, String> map = leadToExcelController.getLeadToFiledPublicQuestionBank();  
            //获取组合excle表头数组，防止重复用的  
            String[] uniqueFields =new String[] {"名称","密码","年龄" };  
            //获取需要导入的具体的表  
            User user = new User();
            //excel转化成的list集合  
            ArrayList list = null;  
            try {  
                //调用excle共用类，转化成list  
                list=ExcelUtil.excelToList(in, user.getClass(), map, uniqueFields); 
            } catch (ExcelException e) {  
                e.printStackTrace();  
            }  
            //保存实体集合  
            int num = this.userService.batchInsert(list);
            if(num > 0){
            	jso.put("success", true);
    			ResponseUtil.write(response, jso);
            }else{
            	 jso.put("success", false);
     			ResponseUtil.write(response, jso);
            }
        } catch (IOException e1) {  
            e1.printStackTrace();
            jso.put("success", false);
			ResponseUtil.write(response, jso);
        }  
		return null;
	}
 
	
	
	
	public static LinkedHashMap<String, String> getLeadToFiledPublicQuestionBank() {  
		  
	    LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();  
	    
	    superClassMap.put("userName", "名称");  
	    superClassMap.put("password", "密码");  
	    superClassMap.put("age", "年龄");  
	    return superClassMap; 
	}
}
