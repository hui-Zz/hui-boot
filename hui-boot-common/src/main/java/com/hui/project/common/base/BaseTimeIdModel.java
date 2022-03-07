package com.hui.project.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * 创建修改时间OutPut父类
 * </p>
 *
 * @author hui
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseTimeIdModel extends BaseIdModel {

	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime createTime;

	@ApiModelProperty(value = "修改时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime updateTime;

	//@ApiModelProperty(value = "创建时间时间戳")
	//private Long createTimeStamp;
	//@ApiModelProperty(value = "修改时间时间戳")
	//private Long updateTimeStamp;
	//
	//public void setCreateTime(LocalDateTime createTime) {
	//	this.createTime = createTime;
	//	if (createTime != null) {
	//		this.createTimeStamp = createTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	//	}
	//}
	//
	//public void setUpdateTime(LocalDateTime updateTime) {
	//	this.updateTime = updateTime;
	//	if (updateTime != null) {
	//		this.updateTimeStamp = updateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	//	}
	//}

}
