package com.hui.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hui.project.model.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author hui
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
