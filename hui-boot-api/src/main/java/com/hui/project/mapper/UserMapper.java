package com.hui.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hui.project.model.entity.sys.User;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hui
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
