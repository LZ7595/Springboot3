package com.itheima.springboot.service;

import com.itheima.springboot.pojo.Jcgy;
import com.itheima.springboot.pojo.PageBean;

import java.util.List;

public interface JcgyQtService {
    PageBean findJcgyQtByPage(int pageNum, int pageSize);

    List<Jcgy> findByIdJcQt(int id);

}
