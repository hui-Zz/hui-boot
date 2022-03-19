package com.hui.project.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hui.project.common.base.BaseController;
import com.hui.project.common.base.Result;
import com.hui.project.common.base.ResultGenerator;
import com.hui.project.model.entity.sys.Role;
import com.hui.project.model.input.CreateRoleInput;
import com.hui.project.model.input.UpdateRoleInput;
import com.hui.project.model.vo.DescribeRolePageVo;
import com.hui.project.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色信息表 前端控制器
 *
 * @author hui
 */
@Api(tags = {"角色信息表"})
@RestController
@RequestMapping(value = "/api/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

@Validated
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("查询角色信息表")
    @GetMapping("/info")
    public Result info(@RequestParam String id) {
        return ResultGenerator.getSuccessResult(roleService.getById(id));
    }

    @ApiOperation("角色信息表列表分页")
    @GetMapping("/list")
    public Result page(DescribeRolePageVo describeRolePageVo) {
        startPage();
        startOrderBy();
        List<Role> list = roleService.list(new LambdaQueryWrapper<Role>());
        return ResultGenerator.getSuccessResult(getDataTable(list));
    }

    @ApiOperation("添加角色信息表")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated CreateRoleInput createRoleInput) {
        Role role = createRoleInput.convert(Role.class);
        return ResultGenerator.getSuccessResult(roleService.save(role));
    }


    @ApiOperation("修改角色信息表")
    @PostMapping("/edit")
    public Result edit(@RequestBody @Validated(UpdateRoleInput.Update.class) UpdateRoleInput updateRoleInput) {
        Role role = updateRoleInput.convert(Role.class);
        Role old = roleService.getById(role.getId());
        Assert.notNull(old, "要修改的数据不存在！");
        return ResultGenerator.getSuccessResult(roleService.updateById(role));
    }


    @ApiOperation("删除角色信息表")
    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        return ResultGenerator.getSuccessResult(roleService.removeById(id));
    }


}
