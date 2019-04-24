package com.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.model.entity.sys.User;

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
