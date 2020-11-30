package com.first.start.common.exception;

public class RSAException extends RuntimeException {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -8703170670740113917L;

	public RSAException(Object Obj) {
		super(Obj.toString());
	}

}
