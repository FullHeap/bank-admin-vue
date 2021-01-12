package com.first.start.project.system.entity;

import java.util.Date;
import java.util.List;

import javax.annotation.sql.DataSourceDefinition;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@TableName(value = "SysUser")
public class SysUser {
	 private static final long serialVersionUID = 1L;

	    /** 用户ID id列自增*/
	    @TableId(value ="userid", type = IdType.AUTO)
	    private Long userId;

	    /** 部门ID */
	    @TableField("deptid")
	    private Long deptId;

	    /** 用户账号 */
	    @TableField("username")
	    private String userName;

	    /** 用户昵称 */
	    @TableField("nickname")
	    private String nickName;

	    /** 用户邮箱 */
	    @TableField("email")
	    private String email;

	    /** 手机号码 */
	    @TableField("phonenumber")
	    private String phonenumber;

	    /** 用户性别 */
	    @TableField("sex")
	    private String sex;

	    /** 用户头像 */
	    @TableField("avatar")
	    private String avatar;

	    /** 密码 */
	    @TableField("password")
	    private String password;

	    /** 盐加密 */
	    @TableField("salt")
	    private String salt;

	    /** 帐号状态（0正常 1停用） */
	    @TableField("status")
	    private String status;

	    /** 删除标志（0代表存在 2代表删除） */
	    @TableField("delflag")
	    private String delFlag;

	    /** 最后登陆IP */
	    @TableField("loginip")
	    private String loginIp;

	    /** 最后登陆时间 */
	    @TableField("logindate")
	    private Date loginDate;

	    /** 部门对象 */
	    @TableField("roles")
	    private List<SysRole> roles;

	    /** 角色组 */
	    @TableField("roleids")
	    private Long[] roleIds;

	    /** 岗位组 */
	    @TableField("postids")
	    private Long[] postIds;

	    public SysUser()
	    {

	    }

	    public SysUser(Long userId)
	    {
	        this.userId = userId;
	    }

	    public Long getUserId()
	    {
	        return userId;
	    }

	    public void setUserId(Long userId)
	    {
	        this.userId = userId;
	    }

	    public boolean isAdmin()
	    {
	        return isAdmin(this.userId);
	    }

	    public static boolean isAdmin(Long userId)
	    {
	        return userId != null && 1L == userId;
	    }

	    public Long getDeptId()
	    {
	        return deptId;
	    }

	    public void setDeptId(Long deptId)
	    {
	        this.deptId = deptId;
	    }

	    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
	    public String getNickName()
	    {
	        return nickName;
	    }

	    public void setNickName(String nickName)
	    {
	        this.nickName = nickName;
	    }

	    @NotBlank(message = "用户账号不能为空")
	    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
	    public String getUserName()
	    {
	        return userName;
	    }

	    public void setUserName(String userName)
	    {
	        this.userName = userName;
	    }

	    @Email(message = "邮箱格式不正确")
	    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	    public String getEmail()
	    {
	        return email;
	    }

	    public void setEmail(String email)
	    {
	        this.email = email;
	    }

	    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
	    public String getPhonenumber()
	    {
	        return phonenumber;
	    }

	    public void setPhonenumber(String phonenumber)
	    {
	        this.phonenumber = phonenumber;
	    }

	    public String getSex()
	    {
	        return sex;
	    }

	    public void setSex(String sex)
	    {
	        this.sex = sex;
	    }

	    public String getAvatar()
	    {
	        return avatar;
	    }

	    public void setAvatar(String avatar)
	    {
	        this.avatar = avatar;
	    }

	    @JsonProperty
	    public String getPassword()
	    {
	        return password;
	    }

	    public void setPassword(String password)
	    {
	        this.password = password;
	    }

	    public String getSalt()
	    {
	        return salt;
	    }

	    public void setSalt(String salt)
	    {
	        this.salt = salt;
	    }

	    public String getStatus()
	    {
	        return status;
	    }

	    public void setStatus(String status)
	    {
	        this.status = status;
	    }

	    public String getDelFlag()
	    {
	        return delFlag;
	    }

	    public void setDelFlag(String delFlag)
	    {
	        this.delFlag = delFlag;
	    }

	    public String getLoginIp()
	    {
	        return loginIp;
	    }

	    public void setLoginIp(String loginIp)
	    {
	        this.loginIp = loginIp;
	    }

	    public Date getLoginDate()
	    {
	        return loginDate;
	    }

	    public void setLoginDate(Date loginDate)
	    {
	        this.loginDate = loginDate;
	    }

	    public List<SysRole> getRoles()
	    {
	        return roles;
	    }

	    public void setRoles(List<SysRole> roles)
	    {
	        this.roles = roles;
	    }

	    public Long[] getRoleIds()
	    {
	        return roleIds;
	    }

	    public void setRoleIds(Long[] roleIds)
	    {
	        this.roleIds = roleIds;
	    }

	    public Long[] getPostIds()
	    {
	        return postIds;
	    }

	    public void setPostIds(Long[] postIds)
	    {
	        this.postIds = postIds;
	    }
	    
	    @Override
	    public String toString() {
	        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("userId", getUserId())
	            .append("deptId", getDeptId())
	            .append("userName", getUserName())
	            .append("nickName", getNickName())
	            .append("email", getEmail())
	            .append("phonenumber", getPhonenumber())
	            .append("sex", getSex())
	            .append("avatar", getAvatar())
	            .append("password", getPassword())
	            .append("salt", getSalt())
	            .append("status", getStatus())
	            .append("delFlag", getDelFlag())
	            .append("loginIp", getLoginIp())
	            .append("loginDate", getLoginDate())
	            .toString();
	    }
}
