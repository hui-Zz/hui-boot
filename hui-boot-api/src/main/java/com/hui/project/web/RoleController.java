package com.hui.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import com.hui.project.common.base.CrudController;
import com.hui.project.model.entity.sys.Role;
import com.hui.project.service.RoleService;

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
