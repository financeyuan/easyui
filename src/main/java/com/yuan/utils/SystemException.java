package com.yuan.utils;



public class SystemException extends SysException {
    
	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 6735670497204143607L;

    /**
     * @param message message
     */
    public SystemException(String message) {
        super(message);
    }
    

    /**
     * @param cause exception
     */
    public SystemException(Throwable cause) {
        super(cause);
    }

}
