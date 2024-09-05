package com.itheima.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
public class FileUploadService {

    public String uploadChunk(MultipartFile chunk, int chunkNumber, String fileName) {
        try {
            Path uploadPath = Paths.get("uploads/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            Files.write(filePath, chunk.getBytes(), StandardOpenOption.APPEND);
            return "Chunk uploaded successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading chunk.";
        }
    }

    public boolean isUploadComplete(String fileName, int totalChunks) {
        Path filePath = Paths.get("uploads/", fileName);
        if (!Files.exists(filePath)) {
            return false;
        }

        try {
            long fileSize = Files.size(filePath);
            // 假设每个分片大小固定为 128MB，根据实际情况调整
            long expectedSize = totalChunks * (1024 * 1024 * 128);
            return fileSize == expectedSize;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String mergeChunks(String fileName, int totalChunks) {
        try {
            Path uploadPath = Paths.get("uploads/");
            Path mergedFilePath = uploadPath.resolve(fileName);
            FileOutputStream fos = new FileOutputStream(mergedFilePath.toFile(), false);

            for (int i = 0; i < totalChunks; i++) {
                Path chunkPath = uploadPath.resolve(fileName + "_chunk_" + i);
                byte[] buffer = Files.readAllBytes(chunkPath);
                fos.write(buffer);
                Files.delete(chunkPath);
            }

            fos.close();
            return "Chunks merged successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error merging chunks.";
        }
    }
}