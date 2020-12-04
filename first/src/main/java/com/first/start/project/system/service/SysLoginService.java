package com.first.start.project.system.service;

import org.springframework.stereotype.Component;

import com.first.start.project.system.model.LoginBody;

/**
 * 登录校验方法
 * 
 */
@Component
public class SysLoginService {

	/**
	 * 登录验证
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param code     验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	public String login(LoginBody loginBody) {
		
		return "true";
	}
}
