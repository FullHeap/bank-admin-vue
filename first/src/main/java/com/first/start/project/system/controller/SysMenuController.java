package com.first.start.project.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单信息
 * 
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

	/* *//**
			 * 获取菜单列表
			 */
	/*
	 * @GetMapping("/list") public AjaxResult list(SysMenu menu) { // List<SysMenu>
	 * menus = menuService.selectMenuList(menu, userId); return
	 * AjaxResult.success(menus); }
	 * 
	 *//**
		 * 根据菜单编号获取详细信息
		 */
	/*
	 * @GetMapping(value = "/{menuId}") public AjaxResult getInfo(@PathVariable Long
	 * menuId) { return AjaxResult.success(menuService.selectMenuById(menuId)); }
	 * 
	 *//**
		 * 获取菜单下拉树列表
		 */
	/*
	 * @GetMapping("/treeselect") public AjaxResult treeselect(SysMenu menu)
	 * 
	 * { Set<String> roles = new HashSet<String>(); Map<String, Object> role = new
	 * HashMap<String, Object>(); role.put("roleName", "管理员"); role.put("roleKey",
	 * "admin"); role.put("roleSort", "1"); role.put("status", "1");
	 * 
	 * roles.add(role.toString());
	 * 
	 * Map<String, Object> user = new HashMap<String, Object>();
	 * user.put("userName", "admin"); user.put("nickName", "lyz"); user.put("email",
	 * "liaoyinzhen@agree.com"); user.put("sex", "1"); user.put("password", "123");
	 * user.put("avatar", ""); user.put("roles", role);
	 * 
	 * AjaxResult ajax = AjaxResult.success();
	 * 
	 * Set<String> permissions = new HashSet<String>(); permissions.add("*:*:*");
	 * 
	 * Set<String> roleadmin = new HashSet<String>(); roleadmin.add("admin");
	 * ajax.put("user", user); ajax.put("roles", roleadmin); ajax.put("permissions",
	 * permissions); LoginUser loginUser =
	 * tokenService.getLoginUser(ServletUtils.getRequest()); Long userId =
	 * loginUser.getUser().getUserId(); List<SysMenu> menus =
	 * menuService.selectMenuList(menu, userId); return
	 * AjaxResult.success(menuService.buildMenuTreeSelect(menus)); }
	 * 
	 *//**
		 * 加载对应角色菜单列表树
		 */
	/*
	 * @GetMapping(value = "/roleMenuTreeselect/{roleId}") public AjaxResult
	 * roleMenuTreeselect(@PathVariable("roleId") Long roleId) { LoginUser loginUser
	 * = tokenService.getLoginUser(ServletUtils.getRequest()); List<SysMenu> menus =
	 * menuService.selectMenuList(loginUser.getUser().getUserId()); AjaxResult ajax
	 * = AjaxResult.success(); ajax.put("checkedKeys",
	 * menuService.selectMenuListByRoleId(roleId)); ajax.put("menus",
	 * menuService.buildMenuTreeSelect(menus)); return ajax; }
	 * 
	 *//**
		 * 新增菜单
		 */
	/*
	 * @Log(title = "菜单管理", businessType = BusinessType.INSERT)
	 * 
	 * @PostMapping public AjaxResult add(@Validated @RequestBody SysMenu menu) { if
	 * (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
	 * return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在"); } else
	 * if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) &&
	 * !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS))
	 * { return AjaxResult.error("新增菜单'" + menu.getMenuName() +
	 * "'失败，地址必须以http(s)://开头"); } menu.setCreateBy(SecurityUtils.getUsername());
	 * return toAjax(menuService.insertMenu(menu)); }
	 * 
	 *//**
		 * 修改菜单
		 */
	/*
	 * @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
	 * 
	 * @PutMapping public AjaxResult edit(@Validated @RequestBody SysMenu menu) { if
	 * (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
	 * return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在"); } else
	 * if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) &&
	 * !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS))
	 * { return AjaxResult.error("新增菜单'" + menu.getMenuName() +
	 * "'失败，地址必须以http(s)://开头"); } else if
	 * (menu.getMenuId().equals(menu.getParentId())) { return
	 * AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己"); }
	 * menu.setUpdateBy(SecurityUtils.getUsername()); return
	 * toAjax(menuService.updateMenu(menu)); }
	 * 
	 *//**
		 * 删除菜单
		 *//*
			 * @Log(title = "菜单管理", businessType = BusinessType.DELETE)
			 * 
			 * @DeleteMapping("/{menuId}") public AjaxResult remove(@PathVariable("menuId")
			 * Long menuId) { if (menuService.hasChildByMenuId(menuId)) { return
			 * AjaxResult.error("存在子菜单,不允许删除"); } if
			 * (menuService.checkMenuExistRole(menuId)) { return
			 * AjaxResult.error("菜单已分配,不允许删除"); } return
			 * toAjax(menuService.deleteMenuById(menuId)); }
			 */
}