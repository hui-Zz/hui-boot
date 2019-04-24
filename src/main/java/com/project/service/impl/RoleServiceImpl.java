package com.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.common.base.BaseServiceImpl;
import com.project.mapper.RoleMapper;
import com.project.model.entity.sys.Role;
import com.project.service.RoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author hui
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

}
