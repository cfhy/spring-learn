package com.yyb.base;

import java.util.List;

/**
 * 基础mapper 所有的mapper都应该实现它
 */
public interface BaseMapper<T> {
    /**
     * 增加
     *
     * @param record record
     * @return id
     */
    Integer insert(T record);

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除数目
     */
    Integer delete(String id);

    /**
     * 通过id查询
     *
     * @param id id
     * @return record
     */
    T findOne(Integer id);

    /**
     * 查询所有
     *@param queryInfo 查询参数
     * @return List<T>
     */
    List<T> findAll(QueryInfo queryInfo);

    /**
     * 更新
     * 通过record (必须包含id)
     *
     * @param record record
     * @return 更新数目
     */
    Integer update(T record);
}
