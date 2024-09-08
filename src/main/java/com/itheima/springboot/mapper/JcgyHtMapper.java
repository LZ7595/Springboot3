package com.itheima.springboot.mapper;

import com.itheima.springboot.pojo.Jcgy;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JcgyHtMapper {

    @Select("select * from jc")
    List<Jcgy> findAllJcHt();

    @Select("select * from jc where id = #{id}")
    List<Jcgy> findByIdJcHt(int id);

    @Select("SELECT * FROM jc WHERE (COALESCE(#{name}, '') = '' OR name LIKE CONCAT('%', #{name}, '%')) AND (COALESCE(#{word}, '') = '' OR word LIKE CONCAT('%', #{word}, '%'))")
    List<Jcgy> findByMoreJcHt(@Param("name") String name, @Param("word") String word);

    @Insert("INSERT INTO jc (name, word, year, img, url) VALUES (#{name}, #{word}, #{year}, #{img}, #{url})")
    int insertJcgy(Jcgy jcgy);

    @Delete("DELETE FROM jc WHERE id = #{id}")
    int deleteJcgyById(@Param("id") int id);

    @Update("UPDATE jc SET name = #{name}, word = #{word}, year = #{year}, img = #{img}, url = #{url}, status = #{status} WHERE id = #{id}")
    int updateJcgyById(Jcgy jcgy);

    @Update("UPDATE jc SET status = #{status} WHERE id = #{id}")
    int updateStatusById( int id,  int status);

    @Select("select * from jc where id = #{id}")
    Jcgy findByIdJcHt2(int id);
}