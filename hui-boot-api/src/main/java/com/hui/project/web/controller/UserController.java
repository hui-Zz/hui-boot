package com.hui.project.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hui.project.model.entity.sys.User;
import com.hui.project.service.UserService;
import com.hui.project.web.CrudController;

import io.swagger.annotations.Api;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hui
 */
@Api(tags = {"用户相关接口"})
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
