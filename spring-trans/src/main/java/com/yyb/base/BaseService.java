package com.yyb.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * service基类，理论上所有service都应该继承
 */
public abstract class BaseService<T> {
    /**
     * 注入Mapper<T>
     */
    @Autowired
    private BaseMapper<T> mapper;

    /**
     * 根据id查询数据
     *
     * @param id
     * @return 不存在返回null
     */
    public T findOne(Integer id) {
        T entity = null;
        entity = mapper.findOne(id);
        return entity;
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return 不存在则所有字段为空
     */
    public T findOrDefault(Integer id, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T entity = this.findOne(id);
        if (entity == null) {
            entity = clazz.newInstance();
        }
        return entity;
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public <V extends QueryInfo> List<T> findAll(V queryInfo) {
        return mapper.findAll(queryInfo);
    }

    /**
     * 分页查询所有数据
     *
     * @return
     */
    public <V extends QueryInfo> PageInfo<T> findPageList(V queryInfo) {
        if (queryInfo.getPageNum() == null || queryInfo.getPageNum() < 1) {
            queryInfo.setPageNum(1);
        }
        List<T> list = null;
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        list = mapper.findAll(queryInfo);
        if (list == null) {
            list = new ArrayList<>();
        }
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 新增数据，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer insert(T record) {
        return mapper.insert(record);
    }

    /**
     * 修改数据，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer update(T record) {
        return mapper.update(record);
    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    public Integer delete(String id) {
        return mapper.delete(id);
    }



}
