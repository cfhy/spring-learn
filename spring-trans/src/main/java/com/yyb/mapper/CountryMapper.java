package com.yyb.mapper;

import com.yyb.base.BaseMapper;
import com.yyb.model.Country;

public interface CountryMapper extends BaseMapper<Country> {

    Country queryOne(int id);

    Integer deleteById(int id);
}