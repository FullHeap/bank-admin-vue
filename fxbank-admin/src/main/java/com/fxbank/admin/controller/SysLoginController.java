package com.fxbank.admin.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
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

//@CrossOrigin(origins = "http://ruoyi.vip", maxAge = 3600)
@RestController
public class SysLoginController
{
    

    /**
     * 登录方法
     * 
     * @param loginBody 用户名密码
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
     * 验证码获取
     * 
     * @param captchaImage 登录信息
     * @return 结果
     */
    @RequestMapping("/captchaImage")
    public AjaxResult captchaImage(String username,String password)
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
    	user.put("avatar", "");
    	user.put("roles", role);
    	
        AjaxResult ajax = AjaxResult.success();
        
        Set<String> permissions = new HashSet<String>();
        permissions.add("*:*:*");
        
        Set<String> roleadmin = new HashSet<String>();
        roleadmin.add("admin");
        ajax.put("user", user);
        ajax.put("roles", roleadmin);
        ajax.put("permissions", permissions);
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
    	
    	Map<String, Object> menuUser = new HashMap<String, Object>();
    	menuUser.put("name", "User");
    	menuUser.put("path", "/user");
    	menuUser.put("hidden", false);
    	menuUser.put("component", "system/user/index");
    	Map<String, Object> metaUser = new HashMap<String, Object>();
    	metaUser.put("title", "用户管理");
    	metaUser.put("icon", "user");
    	menuUser.put("meta", metaUser);
    	children.add(menuUser);
    	
    	Map<String, Object> menuRole = new HashMap<String, Object>();
    	menuRole.put("name", "Role");
    	menuRole.put("path", "/role");
    	menuRole.put("hidden", false);
    	menuRole.put("component", "system/role/index");
    	Map<String, Object> metaRole = new HashMap<String, Object>();
    	metaRole.put("title", "角色管理");
    	metaRole.put("icon", "peoples");
    	menuRole.put("meta", metaRole);
    	children.add(menuRole);
    	
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
