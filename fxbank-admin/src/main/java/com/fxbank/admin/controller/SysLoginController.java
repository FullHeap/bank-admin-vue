package com.fxbank.admin.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxbank.admin.model.AjaxResult;
import com.fxbank.admin.util.IdUtils;

/**
 * 登录验证
 * 
 */
@RestController
public class SysLoginController
{
    

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @RequestMapping("/login")
    public AjaxResult login(String username,String password)
    {
        AjaxResult ajax = AjaxResult.success();
        String token = IdUtils.randomUUID();
        ajax.put("token", token);
        
        return ajax;
    }
    
    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
    	
    	Set<String> roles = new HashSet<String>();
    	Map<String, Object> role = new HashMap<String, Object>();
    	role.put("roleName", "管理员");
    	role.put("roleKey", "admin");
    	role.put("roleSort", "1");
    	role.put("status", "1");
    	
    	roles.add(role.toString());

    	Map<String, Object> user = new HashMap<String, Object>();
    	user.put("userName", "admin");
    	user.put("nickName", "lyz");
    	user.put("email", "liaoyinzhen@agree.com");
    	user.put("sex", "1");
    	user.put("password", "123");
    	user.put("roles", role);
    	
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", "admin");
        ajax.put("permissions", "*:*:*");
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
    	
    	List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
    	Map<String, Object> menu = new HashMap<String, Object>();
    	menu.put("name", "System");
    	menu.put("path", "/system");
    	menu.put("hidden", false);
    	menu.put("redirect", "noRedirect");
    	menu.put("component", "Layout");
    	menu.put("alwaysShow", true);
    	
    	List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
    	
    	Map<String, Object> childrenmenu = new HashMap<String, Object>();
    	childrenmenu.put("name", "User");
    	childrenmenu.put("path", "/system");
    	childrenmenu.put("hidden", false);
    	childrenmenu.put("component", "system/user/index");
    	Map<String, Object> metaUser = new HashMap<String, Object>();
    	metaUser.put("title", "用户管理");
    	metaUser.put("icon", "user");
    	
    	childrenmenu.put("meta", metaUser);
    	
    	children.add(childrenmenu);
    	
    	Map<String, Object> metaSystem = new HashMap<String, Object>();
    	metaSystem.put("title", "系统管理");
    	metaSystem.put("icon", "system");
    	
    	menu.put("meta", metaSystem);
    	menu.put("children", children);
    	
    	AjaxResult ajax = AjaxResult.success();
    	menuList.add(menu);
        ajax.put("data", menuList);
        
        
    	
        return ajax;
    }
    
    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @PostMapping("logout")
    public AjaxResult logout()
    {
    	
    	Set<String> roles = new HashSet<String>();
    	Map<String, Object> role = new HashMap<String, Object>();
    	role.put("roleName", "管理员");
    	role.put("roleKey", "admin");
    	role.put("roleSort", "1");
    	role.put("status", "1");
    	
    	roles.add(role.toString());

    	Map<String, Object> user = new HashMap<String, Object>();
    	user.put("userName", "admin");
    	user.put("nickName", "lyz");
    	user.put("email", "liaoyinzhen@agree.com");
    	user.put("sex", "1");
    	user.put("password", "123");
    	user.put("roles", role);
    	
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", "admin");
        ajax.put("permissions", "*:*:*");
        return ajax;
    }
}
