package com.yuan.log4j;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.FormattedLogger;
import com.p6spy.engine.spy.appender.P6Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackLogger extends FormattedLogger implements P6Logger{
	private static final Logger logger = LoggerFactory.getLogger("p6spy");
	  
    public String getLastEntry() {
        return lastEntry;
    }
  
    public void setLastEntry(String lastEntry) {
        this.lastEntry = lastEntry;
    }
  
    protected String lastEntry;
  
    @Override
    public void logSQL(int connectionId, String s, long l, Category category, String s1,String sql) {
        if (!"resultset".equals(category)) {
            logger.info(trim(sql));
        }
    }
  
    public void logException(Exception e) {
        logger.error(e.getMessage(),e);
    }
  
     public void logText(String s) {
        logger.info(s);
        this.setLastEntry(s);
    }
  
    public boolean isCategoryEnabled(Category category) {
        return true;
    }
  
    private String trim(String sql){
        StringBuilder sb = new StringBuilder("\r\n");
        sb.append(sql.replaceAll("\n|\r|\t|'  '"," "));
        return sb.toString();
    }

}
