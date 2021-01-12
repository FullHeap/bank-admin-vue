package com.first.start.project.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first.start.project.system.entity.SysUser;

@Service
public interface SysLoginService {

	/**
	 * 通过用户名查询用户
	 * 
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	public SysUser selectUserByUserName(String username);

	/**
	 * 修改密码
	 */
	public int updPwd(String username);

	
	
}
