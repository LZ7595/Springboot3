package com.itheima.springboot.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.springboot.mapper.RcdhHtMapper;
import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rcdh;
import com.itheima.springboot.service.RcdhHtService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RcdhHtServiceImpl implements RcdhHtService {
    
    @Resource
    private RcdhHtMapper rcdhHtMapper;

    //分页查询
    @Override
    public PageBean findRcdhHtByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Rcdh> allRcdhHtList = rcdhHtMapper.findAllRcHt();

        Page p = (Page) allRcdhHtList;
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

    public List<Rcdh> findByIdRcHt(int id){
        return rcdhHtMapper.findByIdRcHt(id);
    }

    public PageBean findByMoreRcHtByPage(int pageNum, int pageSize, String name, String word) {
        PageHelper.startPage(pageNum, pageSize);
        List<Rcdh> rcdhList;
        if (name == null && word == null) {
            rcdhList = rcdhHtMapper.findAllRcHt();
        } else {
            rcdhList = rcdhHtMapper.findByMoreRcHt(name, word);
        }
        Page p = (Page) rcdhList;
        long total = p.getTotal();
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

    @Override
    public int insertRcdh(Rcdh rcdh) {
        return rcdhHtMapper.insertRcdh(rcdh);
    }

    @Override
    public int deleteRcdhById(int id) {
        return rcdhHtMapper.deleteRcdhById(id);
    }

    @Override
    public int updateRcdhById(Rcdh rcdh) {
        return rcdhHtMapper.updateRcdhById(rcdh);
    }
}