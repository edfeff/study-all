package com.wpp.oauth2.iotdev.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/file" )
public class UpLoadController {
    @GetMapping
    public String test() {
        return "file OK";
    }

    @PostMapping( "/upload" )
    public String upload(@RequestParam( value = "file" ) MultipartFile file) {
        try {
            int length = file.getBytes().length;
            String originalFilename = file.getOriginalFilename();
            return "file : " + originalFilename + "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file: " + "上传失败";
    }
}
