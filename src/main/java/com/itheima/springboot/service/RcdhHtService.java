package com.itheima.springboot.service;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rcdh;

import java.util.List;

public interface RcdhHtService {

    PageBean findRcdhHtByPage(int pageNum, int pageSize);
    List<Rcdh> findByIdRcHt(int id);
    PageBean findByMoreRcHtByPage(int pageNum, int pageSize, String name, String word);
    int insertRcdh(Rcdh rcdh);
    int deleteRcdhById(int id);
    int updateRcdhById(Rcdh rcdh);
    int updateStatusById(int id, byte status);
    Rcdh findByIdRcHt2(int id);
}