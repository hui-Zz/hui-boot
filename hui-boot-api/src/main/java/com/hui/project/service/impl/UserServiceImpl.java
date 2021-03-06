package com.hui.project.service.impl;

import org.springframework.stereotype.Service;

import com.hui.project.common.base.BaseServiceImpl;
import com.hui.project.mapper.UserMapper;
import com.hui.project.model.entity.sys.User;
import com.hui.project.service.UserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hui
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
