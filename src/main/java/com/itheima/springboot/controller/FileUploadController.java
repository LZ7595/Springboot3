package com.itheima.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Value("F:\\upload")
    private String uploadDirectory;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("请选择要上传的文件。", HttpStatus.BAD_REQUEST);
        }

        try {
            // 创建上传目录（如果不存在）
            Path directoryPath = Paths.get(uploadDirectory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // 生成唯一的文件名
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = directoryPath.resolve(uniqueFileName);

            // 保存文件
            file.transferTo(filePath.toFile());

            return new ResponseEntity<>("文件上传成功！", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("文件上传失败：" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}