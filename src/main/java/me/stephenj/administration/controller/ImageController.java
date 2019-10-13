package me.stephenj.administration.controller;

import me.stephenj.administration.model.Image;
import me.stephenj.administration.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public void show() {
        try{
            imageService.copyHDFSFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
