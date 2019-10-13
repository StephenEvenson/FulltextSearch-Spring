package me.stephenj.administration.controller;

import me.stephenj.administration.model.Image;
import me.stephenj.administration.service.ImageService;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.hadoop.conf.Configuration;

import javax.xml.ws.spi.http.HttpHandler;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestScope
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/imgFile", method = RequestMethod.POST)
    public void imageIdentifyFile(@RequestParam("file") MultipartFile file) {

        String fileName = imageService.uploadFile(file);

        try {
            imageService.copyLocalFile(fileName);

            /*
             * 向python服务请求
             */
            imageService.imageIdentification(fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public void imageIdentify(Image image) {
        imageService.imageIdentification(image.getImage());
    }
}
