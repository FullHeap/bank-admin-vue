package com.first.start.project.system.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.first.start.project.system.entity.SysUser;
import com.first.start.project.system.mapper.SysUserMapper;
import com.first.start.project.system.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserMapper sysusermapper;
	/**
	 * 查询所有用户信息列表
	 * 
	 */
	@Override
	public List<SysUser> selectAll() {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
		List<SysUser> sysuser = sysusermapper.selectList(queryWrapper);
		return sysuser;
	}

	/**
	 * 新增用戶信息
	 */
	@Override
	public SysUser insertUser(SysUser user) {
		SysUser sysuser = new SysUser();
		sysuser.setUserName(user.getUserName());
		sysuser.setEmail(user.getEmail());
		sysuser.setNickName(user.getNickName());
		sysuser.setPassword(user.getPassword());
		sysuser.setPhonenumber(user.getPhonenumber());
		sysuser.setSex(user.getSex());
		sysuser.setStatus(user.getStatus());
		sysuser.setPostIds(user.getPostIds());
		sysuser.setRoleIds(user.getRoleIds());
		sysuser.setRoles(user.getRoles());
		sysuser.setDelFlag(user.getDelFlag());
		int rows = sysusermapper.insert(user);

		if (rows > 0) {

			System.out.println("用户入库成功!");
		}
		return user;
	}

	/**
	 * 刪除用戶
	 */
	@Override
	public void deleteUser(Long userid) {
		// 1.根据主键删除数据
		sysusermapper.deleteById(userid);
//		 2.批量删除
//		List list = Arrays.asList(ids);
//		sysusermapper.deleteBatchIds(list);
		return;
	}

	/**
	 * 修改用戶信息
	 */
	@Override
	public void updateUser(SysUser sysuser) {
		UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("userid", sysuser.getUserId());
		SysUser user = new SysUser();
		user.setEmail(sysuser.getEmail());
		sysusermapper.update(user, updateWrapper);
		return;
	}

}
