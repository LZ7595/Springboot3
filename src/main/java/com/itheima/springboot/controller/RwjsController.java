package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.service.RwjsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RwjsController {
    @Autowired
    private RwjsService rwjsService;

    @GetMapping("/allRw")
    public Result findRwAll(){
        PageBean pageBean = rwjsService.findRwAll();
        return Result.success(pageBean);
    }
}
