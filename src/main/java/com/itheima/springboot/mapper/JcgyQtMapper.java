package com.itheima.springboot.mapper;

import com.itheima.springboot.pojo.Jcgy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JcgyQtMapper {
    @Select("select * from jc where status = 1")
    List<Jcgy> findAllJcQt();

    @Select("select * from jc where status = 1 and id = #{id}")
    List<Jcgy> findByIdJcQt(int id);
}

