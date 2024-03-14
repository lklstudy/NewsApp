package com.lkl.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ImgController {

    @PostMapping("/upload")
    public String handleFileUpload(@RequestBody MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        try {
            // 指定保存文件的路径
            String uploadDir = "C:\\Users\\31033\\Desktop\\bishe\\Project\\NewsApp\\手机报App后端\\src\\main\\resources\\static\\images\\";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = sdf.format(new Date()) + suffix;
            // 保存文件
            String filePath = uploadDir + newFileName;
            file.transferTo(new File(filePath));

            return "http://localhost:9090/images/" + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败：" + e.getMessage();
        }
    }
}