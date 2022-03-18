package com.hui.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hui.project.model.entity.sys.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author hui
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
