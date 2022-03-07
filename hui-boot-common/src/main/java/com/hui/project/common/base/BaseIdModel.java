package com.hui.project.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

;

/**
 * <p>
 * 自增主键父类
 * </p>
 *
 * @author hui
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseIdModel extends BaseConvertModel {

	@NotNull(groups = {Update.class, Describe.class, Delete.class}, message = "id不能为空")
	protected Long id;

}
