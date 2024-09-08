package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.PageBean;
import com.itheima.springboot.pojo.Jcgy;
import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.service.JcgyHtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/jcgyHt")
public class JcgyHtController {

    @Autowired
    private JcgyHtService jcgyHtService;

    @Value("F:\\upload")
    private String uploadDirectory;

    // 修改方法以返回 PageInfo<Rcdh>
    @GetMapping("/allJcHt")
    public Result findAllJcHtByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageBean pageBean = jcgyHtService.findJcgyHtByPage(pageNum, pageSize);
        return Result.success(pageBean);
    }

    @GetMapping("/{id}")
    public Result findByIdJcHt(@PathVariable("id") int id) {
        return Result.success(jcgyHtService.findByIdJcHt(id));
    }

    @GetMapping("/searchByMore")
    public Result searchByMoreJcHt(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "word", required = false) String word) {
        System.out.println("Received parameters: pageNum=" + pageNum + ", pageSize=" + pageSize + ", name=" + name + ", word=" + word);
        PageBean pageBean = jcgyHtService.findByMoreJcHtByPage(pageNum, pageSize, name, word);
        return Result.success(pageBean);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result createJcgy(@RequestParam("img") MultipartFile imgFile,
                             @RequestParam("url") MultipartFile videoFile,
                             @RequestParam("name") String name,
                             @RequestParam("year") int year,
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
            Jcgy jcgy = new Jcgy();
            jcgy.setName(name);
            jcgy.setYear(year);
            jcgy.setWord(word);
            jcgy.setImg(imgUniqueFileName);
            jcgy.setUrl(videoUniqueFileName);

            int result = jcgyHtService.insertJcgy(jcgy);
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
    public Result deleteJcgyById(@PathVariable("id") int id) {
        Jcgy jcgy = jcgyHtService.findByIdJcHt2(id);
        if (jcgy == null) {
            return Result.error("删除失败或记录不存在");
        }
        try {
            Path imgFilePath = Paths.get(uploadDirectory, jcgy.getImg());
            Path videoFilePath = Paths.get(uploadDirectory, jcgy.getUrl());
            if (Files.exists(imgFilePath)) {
                Files.delete(imgFilePath);
            }
            if (Files.exists(videoFilePath)) {
                Files.delete(videoFilePath);
            }
            int result = jcgyHtService.deleteJcgyById(id);
            if (result > 0) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (IOException e) {
            return Result.error("文件删除失败：" + e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result updateJcgy(@RequestParam(value = "img", required = false) MultipartFile imgFile,
                             @RequestParam(value = "url", required = false) MultipartFile videoFile,
                             @RequestParam("name") String name,
                             @RequestParam("year") int year,
                             @RequestParam("word") String word,
                             @RequestParam("id") int id) {
        try {
            // 查找现有的记录
            Jcgy existingJcgy = jcgyHtService.findByIdJcHt2(id);
            if (existingJcgy == null) {
                return Result.error("更新失败，记录不存在");
            }

            // 更新属性
            existingJcgy.setName(name);
            existingJcgy.setYear(year);
            existingJcgy.setWord(word);

            if (imgFile != null && !imgFile.isEmpty()) {
                // 处理图片更新
                // 创建上传目录（如果不存在）
                Path directoryPath = Paths.get(uploadDirectory);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                // 生成唯一的文件名
                String imgUniqueFileName = UUID.randomUUID().toString() + "_" + imgFile.getOriginalFilename();
                Path imgFilePath = directoryPath.resolve(imgUniqueFileName);

                // 保存文件
                imgFile.transferTo(imgFilePath.toFile());

                // 更新图片路径
                existingJcgy.setImg(imgUniqueFileName);
            }

            if (videoFile != null && !videoFile.isEmpty()) {
                // 处理视频更新
                // 创建上传目录（如果不存在）
                Path directoryPath = Paths.get(uploadDirectory);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                // 生成唯一的文件名
                String videoUniqueFileName = UUID.randomUUID().toString() + "_" + videoFile.getOriginalFilename();
                Path videoFilePath = directoryPath.resolve(videoUniqueFileName);

                // 保存文件
                videoFile.transferTo(videoFilePath.toFile());

                // 更新视频路径
                existingJcgy.setUrl(videoUniqueFileName);
            }

            int result = jcgyHtService.updateJcgyById(existingJcgy);
            if (result > 0) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (IOException e) {
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    @PutMapping("/status")
    public Result updateStatus(@RequestParam("status") byte status, @RequestParam("id") int id) {
        Logger logger = LoggerFactory.getLogger(getClass());
        try {
            Jcgy existStatus = jcgyHtService.findByIdJcHt2(id);
            if (existStatus == null) {
                logger.warn("更新失败，记录不存在，ID：{}", id);
                return Result.error("更新失败，记录不存在");
            }
            existStatus.setStatus(status);
            int result = jcgyHtService.updateJcgyById(existStatus);
            if (result > 0) {
                logger.info("更新成功，ID：{}", id);
                return Result.success("更新成功");
            } else {
                logger.error("更新失败，ID：{}", id);
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            logger.error("更新失败，错误信息：{}", e.getMessage(), e);
            return Result.error("更新失败：" + e.getMessage());
        }
    }
}