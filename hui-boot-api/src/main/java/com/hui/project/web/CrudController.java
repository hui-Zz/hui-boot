package com.hui.project.web;

import com.alibaba.fastjson.JSON;
import com.hui.project.common.ResponseMessage;
import com.hui.project.common.base.BaseService;
import com.hui.project.common.utils.ClassUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Arrays;

import static com.hui.project.common.ResponseMessage.created;
import static com.hui.project.common.ResponseMessage.ok;

/**
 * 通用控制器,此控制器实现了通用的增删改查功能
 * 需要提供一个实现了{@link BaseService}接口的实现类
 *
 * @author sunhan
 * @since 2017年02月15日
 */
public abstract class CrudController<PO, PK extends Serializable> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected ServletContext application;

    /**
     * 获取此Controller需要的服务类,由子类实现
     *
     * @return 通用服务类
     */
    protected abstract BaseService<PO> getService();

    /**
     * 获取PO的类型
     *
     * @return PO类型
     */
    protected final Class<PO> getPOType() {
        return (Class<PO>) ClassUtils.getGenericType(this.getClass(), 0);
    }

    /**
     * 根据id（主键）查询数据
     *
     * @param id 主键
     * @return 请求结果
     */
    @ApiOperation(value = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID标识", required = true, paramType = "path", dataType = "long")
    })
    @Cacheable(value = "key")
    @GetMapping(value = "/{id}")
    public @ResponseBody
    Object get(@PathVariable("id") PK id) {
        PO po = getService().getById(id);
        return ok(po);
    }

//    @ApiOperation(value = "分页列表")
//    @GetMapping(path = "/page")
//    public @ResponseBody
//    Object page(Page<PO> page) {
//        return ok(getService().page(page));
//    }

    /**
     * 请求添加数据，请求必须以POST方式
     *
     * @param po 请求添加的对象
     * @return 被添加数据的主键值
     * @throws javax.validation.ValidationException 验证数据格式错误
     */
    @ApiOperation(value = "添加")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage add(PO po) {
        getService().save(po);
        return created(po);
    }

    /**
     * 请求删除指定id的数据，请求方式为DELETE，使用rest风格，如请求 /delete/1 ，将删除id为1的数据
     *
     * @param id 要删除的id标识
     * @return 删除结果
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID标识", required = true, paramType = "path", dataType = "long")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseMessage delete(@PathVariable("id") PK id) {
        PO old = getService().getById(id);
        assertFound(old, "要删除的数据不存在!");
        getService().removeById(id);
        return ok();
    }

    @ApiOperation(value = "批量删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID标识", required = true, paramType = "query", dataType = "long")
    })
    @PostMapping(value = "/batchDelete")
    public ResponseMessage batchDelete(String ids) {
        getService().removeByIds(Arrays.asList(ids.split(",")));
        return ok();
    }

    /**
     * 根据主键修改数据
     *
     * @param id     要修改数据的主键值
     * @param object 要修改的数据
     * @return 请求结果
     */
    @ApiOperation(value = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID标识", required = true, paramType = "path", dataType = "long")
    })
    @PutMapping(value = "/{id}")
    public ResponseMessage update(@PathVariable("id") Integer id, PO object) {
        PO old = getService().getById(id);
        assertFound(old, "要修改的数据不存在!");
        Boolean b = getService().updateById(object);
        return ok(b);
    }

    /**
     * 批量修改数据.
     *
     * @param json 请求修改的数据 json格式
     * @return 被修改数据的条数
     */
    @ApiOperation(value = "批量修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "json", value = "json多对象数据", required = true, paramType = "query", dataType = "string")
    })
    @PutMapping()
    public ResponseMessage update(String json) {
        Boolean success;
        if (json.startsWith("[")) {
            success = getService().updateBatchById(JSON.parseArray(json, getPOType()));
        } else if (json.startsWith("{")) {
            success = getService().updateBatchById(Arrays.asList(JSON.parseObject(json, getPOType())));
        } else {
            throw new RuntimeException("请求数据格式错误!");
        }
        return ok(success);
    }

    /**
     * 判断对象是否为空,如果为空将抛出
     *
     * @param obj 要判断的对象
     * @param msg 为null时异常消息
     */
    public void assertFound(Object obj, String msg) {
        if (obj == null) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * <p>
     * 转换为 bootstrap-table 需要的分页格式 JSON
     * </p>
     *
     * @param page 分页对象
     * @return
     */
    //protected DataTablesPo<PO> getTablesData(Page<PO> page) {
    //    DataTablesPo<PO> bo = new DataTablesPo<>();
    //    bo.setData(page.getRecords());
    //    bo.setDraw(request.getParameter("draw"));
    //    bo.setRecordsTotal(page.getTotal());
    //    bo.setRecordsFiltered(page.getTotal());
    //    return bo;
    //}

    /**
     * <p>
     * 转换为 bootstrap-table 需要的分页格式 JSON
     * </p>
     *
     * @param data 分页对象
     * @return
     */
    //protected DataTablesPo<PO> getTablesDataUnPage(List<PO> data) {
    //    DataTablesPo<PO> bo = new DataTablesPo<>();
    //    bo.setData(data);
    //    bo.setDraw(request.getParameter("draw"));
    //    bo.setRecordsTotal(data.size());
    //    bo.setRecordsFiltered(data.size());
    //    return bo;
    //}

    ///**
    // * <p>
    // * 获取分页对象
    // * </p>
    // *
    // * @return
    // */
    //protected <T> Page<T> getPage() {
    //    int start = 0;
    //    int length = 10;
    //    if (request.getParameter(Const.LENGTH) != null) {
    //        start = Integer.parseInt(request.getParameter(Const.START));
    //    }
    //    if (request.getParameter(Const.LENGTH) != null) {
    //        length = Integer.parseInt(request.getParameter(Const.LENGTH));
    //    }
    //    Page<T> page = new Page<>(start / length + 1, length);
    //    page.setOrderByField("id");
    //    page.setAsc(false);
    //    return page;
    //}
    //protected <T> EntityWrapper<T> getEntityWrapper() {
    //    EntityWrapper<T> ew = new EntityWrapper<>();
    //    ew.where("del_flag={0}", Const.DEL_FLAG_NORMAL);
    //    return ew;
    //}
    //
    ///**
    // * 获取当前登录者对象
    // *
    // * @param <U> the type parameter
    // * @return the current user
    // */
    //@SuppressWarnings("unchecked")
    //public static <U extends UserDetails> U getCurrentUser() {
    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //    return (U) authentication.getPrincipal();
    //}
    //
    //
    ///**
    // * 初始化数据绑定
    // * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
    // * 2. 将字段中Date类型转换为String类型
    // *
    // * @param binder the binder
    // */
    //@InitBinder
    //protected void initBinder(WebDataBinder binder) {
    //    // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
    //    binder.registerCustomEditor(String.class, new StringEditor());
    //    // Date 类型转换
    //    binder.registerCustomEditor(Date.class, new DateEditor());
    //}
}
