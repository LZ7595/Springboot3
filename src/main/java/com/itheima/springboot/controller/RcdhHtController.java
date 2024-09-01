package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rcdh;
import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.service.RcdhHtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rcdhHt")
public class RcdhHtController {

    @Autowired
    private RcdhHtService rcdhHtService;

    // 修改方法以返回PageInfo<Rcdh>
    @GetMapping("/allRcHt")
    public Result findAllRcHtByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageBean pageBean = rcdhHtService.findRcdhHtByPage(pageNum, pageSize);
        return Result.success(pageBean);
    }
    @GetMapping("/{id}")
    public Result findByIdRcHt(@PathVariable("id") int id) {
        return Result.success(rcdhHtService.findByIdRcHt(id));
    }


    @PostMapping("/create")
    public Result createRcdh(@RequestBody Rcdh rcdh) {
        int result = rcdhHtService.insertRcdh(rcdh);
        if (result > 0) {
            return Result.success("创建成功");
        } else {
            return Result.error("创建失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteRcdhById(@PathVariable("id") int id) {
        int result = rcdhHtService.deleteRcdhById(id);
        if (result > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败或记录不存在");
        }
    }

    @PutMapping("/update")
    public Result updateRcdh(@RequestBody Rcdh rcdh) {
        int result = rcdhHtService.updateRcdhById(rcdh);
        if (result > 0) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败或记录不存在");
        }
    }
}