package com.itheima.springboot.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.springboot.mapper.JcgyHtMapper;
import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Jcgy;
import com.itheima.springboot.service.JcgyHtService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JcgyHtServiceImpl implements JcgyHtService {
    
    @Resource
    private JcgyHtMapper jcgyHtMapper;

    //分页查询
    @Override
    public PageBean findJcgyHtByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Jcgy> allRcdhHtList = jcgyHtMapper.findAllJcHt();

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

    public List<Jcgy> findByIdJcHt(int id){
        return jcgyHtMapper.findByIdJcHt(id);
    }

    public PageBean findByMoreJcHtByPage(int pageNum, int pageSize, String name, String word) {
        PageHelper.startPage(pageNum, pageSize);
        List<Jcgy> jcgyList;
        if (name == null && word == null) {
            jcgyList = jcgyHtMapper.findAllJcHt();
        } else {
            jcgyList = jcgyHtMapper.findByMoreJcHt(name, word);
        }
        Page p = (Page) jcgyList;
        long total = p.getTotal();
        int pageI = p.getPageNum();
        int pageS = p.getPages();
        int size = p.getPageSize();
        PageBean pageBean = new PageBean();
        pageBean.setTotal(total);
        pageBean.setRows(jcgyList);
        pageBean.setPageNum(pageI);
        pageBean.setPages(pageS);
        pageBean.setPageSize(size);
        return pageBean;
    }

    @Override
    public int insertJcgy(Jcgy jcgy) {return jcgyHtMapper.insertJcgy(jcgy);}

    @Override
    public int deleteJcgyById(int id) {
        return jcgyHtMapper.deleteJcgyById(id);
    }

    @Override
    public int updateJcgyById(Jcgy jcgy) {
        return jcgyHtMapper.updateJcgyById(jcgy);
    }

    @Override
    public int updateStatusById(int id, byte status) {return jcgyHtMapper.updateStatusById(id, status);}

    @Override
    public Jcgy findByIdJcHt2(int id){return jcgyHtMapper.findByIdJcHt2(id);}
}