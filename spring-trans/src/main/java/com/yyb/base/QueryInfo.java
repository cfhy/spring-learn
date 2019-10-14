package com.yyb.base;

/**
 * @author: yyb
 * @date: 2018/8/30 16:49
 * @description: 查询参数
 */
public class QueryInfo {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 页容量
     */
    private Integer pageSize = 10;

    /**
     * 状态
     */
    private Integer status;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

