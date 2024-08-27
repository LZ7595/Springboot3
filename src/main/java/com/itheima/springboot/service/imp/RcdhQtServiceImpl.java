package com.itheima.springboot.service.imp;

import com.github.pagehelper.Page;
import com.itheima.springboot.mapper.RcdhQtMapper;
import com.github.pagehelper.PageHelper;
import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rcdh;
import com.itheima.springboot.service.RcdhQtService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RcdhQtServiceImpl implements RcdhQtService
{
    @Resource
    private RcdhQtMapper rcdhQtMapper;

    //分页查询
    @Override
    public PageBean findRcdhQtByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Rcdh> allRcdhQtList = rcdhQtMapper.findAllRcQt();

        Page p = (Page) allRcdhQtList;
        long total = p.getTotal();
        List rcdhList =  p.getResult();
        int pageI = p.getPageNum();
        int pageS = p.getPages();
        int size = p.getPageSize();

        PageBean pageBean = new PageBean();
        pageBean.setTotal(total);
        pageBean.setRows(rcdhList);
        pageBean.setPageNum(pageI);
        pageBean.setPages(pageS);
        pageBean.setPageSize(size);

        return pageBean;
    }

        public List<Rcdh> findByIdRcQt(int id){
            return rcdhQtMapper.findByIdRcQt(id);
    }
}
