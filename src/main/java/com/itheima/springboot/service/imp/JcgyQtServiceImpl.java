package com.itheima.springboot.service.imp;

import com.github.pagehelper.Page;
import com.itheima.springboot.mapper.JcgyQtMapper;
import com.github.pagehelper.PageHelper;
import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Jcgy;
import com.itheima.springboot.service.JcgyQtService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JcgyQtServiceImpl implements JcgyQtService {

    @Resource
    private JcgyQtMapper jcgyQtMapper;

    //分页查询
    @Override
    public PageBean findJcgyQtByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Jcgy> allJcgyQtList = jcgyQtMapper.findAllJcQt();

        Page p = (Page) allJcgyQtList;
        long total = p.getTotal();
        List jcgyList = p.getResult();
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

    public List<Jcgy> findByIdJcQt(int id){
        return jcgyQtMapper.findByIdJcQt(id);
    }
}
