package com.yuan.log4j;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.P6Logger;
public class P6SpyLogger implements P6Logger {
	private static final Logger logger = LoggerFactory.getLogger("p6spy");
    private String lastEntry;
    
    public String getLastEntry() {
        return lastEntry;
    }
 
    public void setLastEntry(String lastEntry) {
        this.lastEntry = lastEntry;
    }
 
    public void logException(Exception e) {
        logger.debug(e.getMessage(),e);
    }
 
    public void logSQL(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        if (!"resultset".equals(category) && sql.length() > 0) {
            // FormatStyle.BASIC.getFormatter().format(sql);启用hibernate的sql美化
        	logger.debug( formatSql(sql));
        }
    }
 
    public void logText(String text) {
        logger.debug(text);
        lastEntry = text;
    }
 
    /**
     * @Title: formatSql
     * @Description: 格式化sql
     * @author ShaoChangxing
     * @date 2015-5-8 下午12:37:51
     */
    public static String formatSql(String sql) {
        //匹配长度超过5个字符的别名
        Pattern p = Pattern.compile("[a-zA-Z0-9_]{5,}_\\.");
        Matcher m = p.matcher(sql);
        List<String> matchs = new ArrayList<String>();
        while (m.find()) {
            String match = m.group();
            if (!matchs.contains(match)) {
                matchs.add(match.split("\\.")[0]);
            }
        }
        //替换表别名从a开始
        char i = 'a';
        for (String str : matchs) {
            sql = sql.replaceAll(str + "\\.", i + ".");
            sql = sql.replaceAll(str, i + "");
            i += 1;
        }
        //替换有as的字段别名
        sql = sql.replaceAll(" as [a-zA-Z0-9_]{5,}_", "");
        return sql;
    }

	public void logSQL(int connectionId, String now, long elapsed,
			Category category, String prepared, String sql) {
		
	}

	public boolean isCategoryEnabled(Category category) {
		return false;
	}
}