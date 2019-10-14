package com.yyb.mapper;

import com.yyb.base.BaseMapper;
import com.yyb.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper extends BaseMapper<Student> {

    @Insert("${sql}")
    void insertSql(@Param("sql") String sql);
}