package com.hui.project.model.entity.sys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hui.project.common.base.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author hui
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role extends BaseModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "归属机构")
	private Long officeId;

	@ApiModelProperty(value = "角色名称")
	private String name;

	@ApiModelProperty(value = "英文名称")
	private String enname;

	@ApiModelProperty(value = "角色类型")
	private String roleType;

	@ApiModelProperty(value = "数据范围")
	private String dataScope;

	@ApiModelProperty(value = "是否系统数据")
	private String isSys;

	@ApiModelProperty(value = "是否可用")
	private String useable;

	@ApiModelProperty(value = "创建者")
	@TableField(fill = FieldFill.INSERT)
	private Long createBy;

	@ApiModelProperty(value = "更新者")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateBy;

	@ApiModelProperty(value = "备注信息")
	private String remarks;

	@ApiModelProperty(value = "是否删除：0-否；1-删除")
	@TableLogic
	private Boolean delFlag;


}
