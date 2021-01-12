package com.first.start.project.system.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.start.project.system.entity.SysRole;
import com.first.start.project.system.mapper.SysRoleMapper;
import com.first.start.project.system.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper sysrolemapper;

	/**
	 * 新增角色
	 */
	@Override
	public void insertrole(SysRole sysrole) {
		SysRole role = new SysRole();
		role.setRoleId(sysrole.getRoleId());
		role.setRoleKey(sysrole.getRoleKey());
		role.setRoleName(sysrole.getRoleName());
		role.setRoleSort(sysrole.getRoleSort());
		int rows = sysrolemapper.insert(role);
		if (rows > 0) {
			System.out.println("新增数据成功");
		}
	}

	/**
	 * 删除角色
	 */
	@Override
	public void delrole(Long roleid) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("roleid", roleid);
		sysrolemapper.deleteByMap(map);
	}
}
