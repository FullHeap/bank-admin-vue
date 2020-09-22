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
		
		Set<Object> roles = new HashSet<Object>();
    	Map<String, Object> role1 = new HashMap<String, Object>();
    	role1.put("roleId", "1");
    	role1.put("roleName", "管理员");
    	role1.put("roleKey", "admin");
    	role1.put("roleSort", "1");
    	role1.put("status", "0");
    	role1.put("remark", "超级管理员");
    	role1.put("createTime", "2018-03-16 11:33:00");
    	
    	
    	Map<String, Object> role2 = new HashMap<String, Object>();
    	role2.put("roleId", "2");
    	role2.put("roleName", "管理员");
    	role2.put("roleKey", "admin");
    	role2.put("roleSort", "1");
    	role2.put("status", "0");
    	role2.put("remark", "超级管理员");
    	role2.put("createTime", "2018-03-16 11:33:00");
    	
    	roles.add(role1);
    	roles.add(role2);
    	
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
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}

	/**
	 * 删除角色
	 */
	@DeleteMapping("/{roleIds}")
	public AjaxResult remove(@PathVariable Long[] roleIds) {
		for (Long long1 : roleIds) {
			System.out.println(long1);
		}
		
		return new AjaxResult();
	}
	
	@GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId)
    {
		Map<String, Object> role2 = new HashMap<String, Object>();
		Map<String, Object> role1 = new HashMap<String, Object>();
		AjaxResult ajax = AjaxResult.success();
		if(roleId==1) {
			
	    	role1.put("roleId", "1");
	    	role1.put("roleName", "管理员");
	    	role1.put("roleKey", "admin");
	    	role1.put("roleSort", "1");
	    	role1.put("status", "0");
	    	role1.put("remark", "超级管理员");
	    	role1.put("createTime", "2018-03-16 11:33:00");
	    	ajax.put("data", role1);
		}
		else {
			
	    	role2.put("roleId", "2");
	    	role2.put("roleName", "管理员");
	    	role2.put("roleKey", "admin");
	    	role2.put("roleSort", "1");
	    	role2.put("status", "0");
	    	role2.put("remark", "超级管理员");
	    	role2.put("createTime", "2018-03-16 11:33:00");
	    	ajax.put("data", role2);
		}
    	
        return ajax;
    }
}