package com.hui.project.web.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.PhoneUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hui.project.common.base.BaseController;
import com.hui.project.common.base.Result;
import com.hui.project.common.base.ResultGenerator;
import com.hui.project.model.dto.UserDto;
import com.hui.project.model.dto.UserInput;
import com.hui.project.model.dto.UserPageDto;
import com.hui.project.model.entity.sys.User;
import com.hui.project.model.vo.UserInfoVo;
import com.hui.project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息表 前端控制器
 *
 * @author hui
 */
@Api(tags = {"用户信息表"})
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询用户信息表")
    @GetMapping("/info")
    public Result info(@RequestParam String id) {
        User user = userService.getById(id);
        UserInfoVo userInfoVo = user.convert(UserInfoVo.class);
        userInfoVo.setPhonenumber((String) PhoneUtil.hideBetween(userInfoVo.getPhonenumber()));
        return ResultGenerator.getSuccessResult(userInfoVo);
    }

    @ApiOperation("用户信息表列表分页")
    @GetMapping("/list")
    public Result page(UserPageDto dto) {
        startPage();
        startOrderBy();
        List<User> list = userService.list(new LambdaQueryWrapper<User>());
        return ResultGenerator.getSuccessResult(getDataTable(list));
    }

    @ApiOperation("添加用户信息表")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated UserInput input) {
        User user = input.convert(User.class);
        return ResultGenerator.getSuccessResult(userService.save(user));
    }


    @ApiOperation("修改用户信息表")
    @PostMapping("/edit")
    public Result edit(@RequestBody @Validated(UserDto.Update.class) UserDto dto) {
        User user = dto.convert(User.class);
        User old = userService.getById(user.getId());
        Assert.notNull(old, "要修改的数据不存在！");
        return ResultGenerator.getSuccessResult(userService.updateById(user));
    }


    @ApiOperation("删除用户信息表")
    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        return ResultGenerator.getSuccessResult(userService.removeById(id));
    }


}
