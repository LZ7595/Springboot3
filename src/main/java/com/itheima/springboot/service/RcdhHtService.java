package com.itheima.springboot.service;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rcdh;

import java.util.List;

public interface RcdhHtService {

    PageBean findRcdhHtByPage(int pageNum, int pageSize);
    List<Rcdh> findByIdRcHt(int id);

    int insertRcdh(Rcdh rcdh);
    int deleteRcdhById(int id);
    int updateRcdhById(Rcdh rcdh);
}