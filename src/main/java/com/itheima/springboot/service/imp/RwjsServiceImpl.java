package com.itheima.springboot.service.imp;

import com.itheima.springboot.mapper.RwjsMapper;
import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rwjs;
import com.itheima.springboot.service.RwjsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RwjsServiceImpl implements RwjsService {

    @Resource
    private RwjsMapper rwjsMapper;

    @Override
    public PageBean findRwAll(){
        List<Rwjs> allRwjsList = rwjsMapper.findRwAll();
        PageBean pageBean = new PageBean();
        pageBean.setRows(allRwjsList);

        return pageBean;

    }
}
