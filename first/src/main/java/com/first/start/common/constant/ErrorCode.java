package com.first.start.common.constant;

public enum ErrorCode {
	
	
	/**
	* @Fields RSA000_ERROR : RSA未知错误
	*/
	RSA000_ERROR("RSA000","RSA未知错误"),
	/**
	* @Fields RSA001_ERROR : RSA加密解密错误
	*/
	RSA001_ERROR("RSA001","RSA加密解密错误"),
	/**
	* @Fields RSA002_ERROR : RSA加签验签错误
	*/
	RSA002_ERROR("RSA002","RSA加签验签错误"),
	
	
	/**
	* @Fields UNKNOWN_ERROR : 系统未知错误
	*/
	UNKNOWN_ERROR("999999","系统未知错误");
	
	private String code;
    private String desc;

    private ErrorCode(String code, String desc) {
        this.setCode(code);
        this.setDesc(desc);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.desc;
    }
}
