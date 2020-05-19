package me.stephenj.administration.controller;

import me.stephenj.administration.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("file")
    @ResponseBody
    public int uploadFile(@RequestParam(value = "file", required = true)MultipartFile file) {
        try {
            return uploadService.uploadFile(file);
        } catch (Exception e) {
            return 0;
        }
    }

}
