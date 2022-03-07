package com.hui.project.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseTimeModel extends BaseConvertModel {

	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "修改时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	//private Long createTimeStamp;
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
