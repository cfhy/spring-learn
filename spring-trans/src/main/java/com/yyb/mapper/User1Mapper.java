package com.yyb.mapper;

import com.yyb.model.User1;
import org.apache.ibatis.annotations.Insert;

public interface User1Mapper {
    @Insert("insert into user1(name)values(#{name})")
    int insert(User1 record);
    @Insert("select * from user1 where id=#{id}")
    User1 selectByPrimaryKey(Integer id);
}
