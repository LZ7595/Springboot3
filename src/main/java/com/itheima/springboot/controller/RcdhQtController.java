package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.service.RcdhQtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rcdh")
public class RcdhQtController {

    @Autowired
    private RcdhQtService rcdhQtService;

    // 修改方法以返回PageInfo<Rcdh>
    @GetMapping("/allRcQt")
    public Result findAllRcQtByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        PageBean pageBean = rcdhQtService.findRcdhQtByPage(pageNum, pageSize);
        return Result.success(pageBean);
    }
    @GetMapping("/{id}")
    public Result findByIdRcQt(@PathVariable("id") int id) {
        return Result.success(rcdhQtService.findByIdRcQt(id));
    }
}
