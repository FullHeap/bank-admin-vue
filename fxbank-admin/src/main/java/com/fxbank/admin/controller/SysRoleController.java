package com.fxbank.admin.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxbank.admin.model.AjaxResult;
import com.fxbank.admin.model.SysRole;

/**
 * 角色信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

	@GetMapping("/list")
	public AjaxResult list() {
		
		Set<String> roles = new HashSet<String>();
    	Map<String, Object> role1 = new HashMap<String, Object>();
    	role1.put("roleId", "1");
    	role1.put("roleName", "管理员");
    	role1.put("roleKey", "admin");
    	role1.put("roleSort", "1");
    	role1.put("status", "1");
    	role1.put("remark", "超级管理员");
    	role1.put("createTime", "2018-03-16 11:33:00");
    	
    	
    	Map<String, Object> role2 = new HashMap<String, Object>();
    	role1.put("roleId", "2");
    	role2.put("roleName", "管理员");
    	role2.put("roleKey", "admin");
    	role2.put("roleSort", "1");
    	role2.put("status", "1");
    	role2.put("remark", "超级管理员");
    	role2.put("createTime", "2018-03-16 11:33:00");
    	
    	roles.add(role1.toString());
    	roles.add(role2.toString());
    	
        AjaxResult ajax = AjaxResult.success();
        ajax.put("rows", roles);
        return ajax;
	}

	/**
	 * 新增角色
	 */
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysRole role) {
		System.out.println(role);
		return new AjaxResult();
	}

	/**
	 * 删除角色
	 */
	@DeleteMapping("/{roleIds}")
	public AjaxResult remove(@PathVariable Long[] roleIds) {
		return new AjaxResult();
	}
}