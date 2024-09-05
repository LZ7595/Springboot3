package com.itheima.springboot.mapper;

import com.itheima.springboot.pojo.Rcdh;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RcdhHtMapper {

    @Select("select * from rc")
    List<Rcdh> findAllRcHt();

    @Select("select * from rc where id = #{id}")
    List<Rcdh> findByIdRcHt(int id);

    @Select("SELECT * FROM rc WHERE (COALESCE(#{name}, '') = '' OR name LIKE CONCAT('%', #{name}, '%')) AND (COALESCE(#{word}, '') = '' OR word LIKE CONCAT('%', #{word}, '%'))")
    List<Rcdh> findByMoreRcHt(@Param("name") String name, @Param("word") String word);

    @Insert("INSERT INTO rc (name, word, num, img, url) VALUES (#{name}, #{word}, #{num}, #{img}, #{url})")
    int insertRcdh(Rcdh rcdh);

    @Delete("DELETE FROM rc WHERE id = #{id}")
    int deleteRcdhById(@Param("id") int id);

    @Update("UPDATE rc SET name = #{name}, word = #{word}, num = #{num}, img = #{img}, url = #{url}, status = #{status} WHERE id = #{id}")
    int updateRcdhById(Rcdh rcdh);
}