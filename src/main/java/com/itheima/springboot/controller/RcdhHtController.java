package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Rcdh;
import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.service.RcdhHtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/rcdhHt")
public class RcdhHtController {

    @Autowired
    private RcdhHtService rcdhHtService;

    @Value("F:\\upload")
    private String uploadDirectory;

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

    @GetMapping("/searchByMore")
    public Result searchByMoreRcHt(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "word", required = false) String word) {
        System.out.println("Received parameters: pageNum=" + pageNum + ", pageSize=" + pageSize + ", name=" + name + ", word=" + word);
        PageBean pageBean = rcdhHtService.findByMoreRcHtByPage(pageNum, pageSize, name, word);
        return Result.success(pageBean);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result createRcdh(@RequestParam("img") MultipartFile imgFile,
                             @RequestParam("url") MultipartFile videoFile,
                             @RequestParam("name") String name,
                             @RequestParam("num") int num,
                             @RequestParam("word") String word) {
        if (imgFile.isEmpty() || videoFile.isEmpty()) {
            return Result.error("请选择要上传的图片和视频。");
        }

        try {
            // 创建上传目录（如果不存在）
            Path directoryPath = Paths.get(uploadDirectory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // 生成唯一的文件名
            String imgUniqueFileName = UUID.randomUUID().toString() + "_" + imgFile.getOriginalFilename();
            String videoUniqueFileName = UUID.randomUUID().toString() + "_" + videoFile.getOriginalFilename();
            Path imgFilePath = directoryPath.resolve(imgUniqueFileName);
            Path videoFilePath = directoryPath.resolve(videoUniqueFileName);

            // 保存文件
            imgFile.transferTo(imgFilePath.toFile());
            videoFile.transferTo(videoFilePath.toFile());

            // 创建 Rcdh 对象并保存到数据库
            Rcdh rcdh = new Rcdh();
            rcdh.setName(name);
            rcdh.setNum(num);
            rcdh.setWord(word);
            rcdh.setImg(imgUniqueFileName);
            rcdh.setUrl(videoUniqueFileName);

            int result = rcdhHtService.insertRcdh(rcdh);
            if (result > 0) {
                return Result.success("创建成功");
            } else {
                return Result.error("创建失败");
            }
        } catch (IOException e) {
            return Result.error("文件上传失败：" + e.getMessage());
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