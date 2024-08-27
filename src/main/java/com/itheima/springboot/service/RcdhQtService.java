package com.itheima.springboot.service;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rcdh;

import java.util.List;

public interface RcdhQtService {

    PageBean findRcdhQtByPage(int pageNum, int pageSize);
    List<Rcdh> findByIdRcQt(int id);
}
