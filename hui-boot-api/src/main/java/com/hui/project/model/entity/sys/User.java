package com.hui.project.model.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.hui.project.common.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hui
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "用户表")
public class User extends BaseModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "归属公司")
	private Long companyId;

	@ApiModelProperty(value = "归属部门")
	private Long officeId;

	@ApiModelProperty(value = "登录名")
	private String loginName;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "工号")
	private String no;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "电话")
	private String phone;

	@ApiModelProperty(value = "手机")
	private String mobile;

	@ApiModelProperty(value = "用户类型")
	private String userType;

	@ApiModelProperty(value = "用户头像")
	private String photo;

	@ApiModelProperty(value = "用户头像缩略图")
	private String thumb;

	@ApiModelProperty(value = "最后登陆IP")
	private String loginIp;

	@ApiModelProperty(value = "最后登陆时间")
	private LocalDateTime loginDate;

	@ApiModelProperty(value = "是否可登录：0-不可登录；1-可登录")
	private String loginFlag;

	@ApiModelProperty(value = "创建者")
	@TableField(fill = FieldFill.INSERT)
	private String createBy;

	@ApiModelProperty(value = "更新者")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateBy;

	@ApiModelProperty(value = "备注信息")
	private String remarks;

	@ApiModelProperty(value = "是否删除：0-否；1-删除")
	@TableLogic
	private Boolean delFlag;


}
