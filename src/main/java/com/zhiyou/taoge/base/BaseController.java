package com.zhiyou.taoge.base;

import com.zhiyou.taoge.common.utils.PageUtil;
import com.zhiyou.taoge.common.utils.ResponseUtil;
import com.zhiyou.taoge.common.vo.PageVo;
import com.zhiyou.taoge.common.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

public abstract class BaseController<E, ID extends Serializable> {

    @Autowired
    public abstract BaseService<E, ID> getService();

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过id获取")
    public Result<E> get(@PathVariable ID id) {
        E entity = getService().get(id);
        return ResponseUtil.ok(entity);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    public Result<List<E>> getAll() {

        List<E> list = getService().getAll();
        return ResponseUtil.ok(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public Result<Page<E>> getByPage(PageVo page) {
        Page<E> data = getService().findAll(PageUtil.initPage(page));
        return ResponseUtil.ok(data);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public Result<E> save(E entity) {
        E e = getService().save(entity);
        return ResponseUtil.ok(e);
    }

    @RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@PathVariable ID[] ids) {
        for (ID id : ids) {
            getService().delete(id);
        }
        return ResponseUtil.ok();
    }

    @RequestMapping(value = "/delById/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "通过id删除")
    public Result<Object> delAllByIds(@PathVariable ID id) {
        getService().delete(id);
        return ResponseUtil.ok();
    }
}
