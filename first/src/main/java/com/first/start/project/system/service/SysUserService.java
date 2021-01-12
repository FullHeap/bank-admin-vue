package com.first.start.project.system.service;

import java.util.List;

import com.first.start.project.system.entity.SysUser;

public interface SysUserService {
	/**
	 * 查询用户列表信息
	 *
	 */

	public List<SysUser> selectAll();

	/**
	 * 新增用户
	 * 
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	public SysUser insertUser(SysUser user);

	/**
	 * 删除用户
	 * 
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	public void deleteUser(Long userid);

	/**
	 * 修改用户
	 * 
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	public void updateUser(SysUser sysuser);

}
