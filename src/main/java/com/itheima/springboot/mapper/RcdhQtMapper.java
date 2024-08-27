package com.itheima.springboot.mapper;

import com.itheima.springboot.pojo.Rcdh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RcdhQtMapper {

    @Select("select * from rc where status = 1")
    List<Rcdh> findAllRcQt();

    @Select("select * from rc where status = 1 and id = #{id}")
    List<Rcdh> findByIdRcQt(int id);
}
