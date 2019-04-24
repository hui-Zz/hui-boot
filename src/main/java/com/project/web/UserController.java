package com.project.web;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import com.project.common.base.CrudController;
import com.project.model.entity.sys.User;
import com.project.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hui
 */
@Api(tags = {"User"}, description = "用户表相关接口")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class UserController extends CrudController<User, Long> {

	@Autowired
	private UserService userService;

	@Override
	protected UserService getService() {
		return userService;
	}

	@RequestMapping("/uid")
	String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}

}
