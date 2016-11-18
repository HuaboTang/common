package org.codrim.common.utils.exception;

import org.codrim.common.utils.enums.ResultEnum;

/**
 * 业务层操作错误异常处理类
 * @author tanghuabo
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1832959537260077487L;
	
	/** 错误代码 */
	private int errorCode = ResultEnum.Error;
	
	public ServiceException() {
	}
	
	public ServiceException(String errorMsg) {
		super(errorMsg);
	}
	
	public ServiceException(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
