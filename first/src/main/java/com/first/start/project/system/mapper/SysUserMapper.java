package com.first.start.project.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.first.start.project.system.entity.SysUser;
@Mapper
public interface SysUserMapper  extends BaseMapper<SysUser>  {

}
