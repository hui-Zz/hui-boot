package com.hui.project.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 分页属性父类
 * </p>
 *
 * @author hui
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BasePageModel extends BaseConvertModel {

    @ApiModelProperty(value = "分页对象", notes = "分页对象")
    protected PageModel page;

}
