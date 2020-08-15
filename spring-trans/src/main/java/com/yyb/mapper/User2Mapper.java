package com.yyb.mapper;

import com.yyb.model.User2;
import org.apache.ibatis.annotations.Insert;

public interface User2Mapper {
    @Insert("insert into user2(name)values(#{name})")
    int insert(User2 record);
    @Insert("select * from user2 where id=#{id}")
    User2 selectByPrimaryKey(Integer id);
}
