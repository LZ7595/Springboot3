package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.service.JcgyQtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jcgy")
public class JcgyQtController {

    @Autowired
    private JcgyQtService jcgyQtService;

    // 修改方法以返回PageInfo<Rcdh>
    @GetMapping("/allJcQt")
    public Result findAllJcQtByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        PageBean pageBean = jcgyQtService.findJcgyQtByPage(pageNum, pageSize);
        return Result.success(pageBean);
    }
    @GetMapping("/{id}")
    public Result findByIdJcQt(@PathVariable("id") int id) {
        return Result.success(jcgyQtService.findByIdJcQt(id));
    }
}
