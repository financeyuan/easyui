package com.yuan.utils;


/**
 *
 */
public class SysException extends RuntimeException {
    
	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 4818777027960841733L;

    /**
     * responseData
     */
    private ResponseData responseData;
    
    /**
     * @param responseData responseData
     */
    public SysException(ResponseData responseData) {
        setResponseData(responseData);
    }

    /**
     * @param cause Throwable
     */
    public SysException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message message
     */
    public SysException(String message) {
        super(message);
    }
    
    /**
     * @return response_data  
     */
    public ResponseData getResponseData() {
        return responseData;
    }
       
    /**
     * @param responseData 
     */
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

}
