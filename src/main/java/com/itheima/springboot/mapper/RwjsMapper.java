package com.itheima.springboot.mapper;

import com.itheima.springboot.pojo.Rwjs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface RwjsMapper {
    @Select("select * from rwjs")
    List<Rwjs> findRwAll();
}
