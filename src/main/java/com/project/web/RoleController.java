package com.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import com.project.common.base.CrudController;
import com.project.model.entity.sys.Role;
import com.project.service.RoleService;

import io.swagger.annotations.Api;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author hui
 */
@Api(tags = {"Role"}, description = "角色表相关接口")
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class RoleController extends CrudController<Role, Long> {

	@Autowired
	private RoleService roleService;

	@Override
	protected RoleService getService() {
		return roleService;
	}


}
