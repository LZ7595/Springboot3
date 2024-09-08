package com.itheima.springboot.service;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Jcgy;

import java.util.List;

public interface JcgyHtService {

    PageBean findJcgyHtByPage(int pageNum, int pageSize);
    List<Jcgy> findByIdJcHt(int id);
    PageBean findByMoreJcHtByPage(int pageNum, int pageSize, String name, String word);
    int insertJcgy(Jcgy Jcgy);
    int deleteJcgyById(int id);
    int updateJcgyById(Jcgy jcgy);
    int updateStatusById(int id, byte status);
    Jcgy findByIdJcHt2(int id);
}