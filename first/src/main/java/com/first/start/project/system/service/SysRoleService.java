package com.first.start.project.system.service;

import com.first.start.project.system.entity.SysRole;

public interface SysRoleService {

	
	/**
	 * 新增角色信息
	 */
	public void insertrole(SysRole sysrole);

	
	/**
	 * 删除角色
	 */
	public void delrole(Long roleid);
}
