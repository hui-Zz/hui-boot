package com.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.common.base.BaseServiceImpl;
import com.project.mapper.UserMapper;
import com.project.model.entity.sys.User;
import com.project.service.UserService;

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
