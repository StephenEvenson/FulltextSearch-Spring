package me.stephenj.administration.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadService {
    private static String rootPath = "/home/stephen/solr/myfile";

    public int uploadFile(MultipartFile file) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileDir = sdf.format(new Date());
        File filePath = new File(rootPath + File.separator + fileDir + File.separator);
        if (!filePath.exists()) {
            filePath.mkdir();
        }

        String filename = file.getOriginalFilename();
        String[] filenameArray = filename.split("\\.");
        String prefix = filenameArray[filenameArray.length - 2];
        String suffix = "." + filenameArray[filenameArray.length - 1];
        File newFile = new File(filePath, prefix + suffix);
        for (int i = 1; newFile.exists() && i < Integer.MAX_VALUE; i++) {
            newFile = new File(filePath, prefix + '(' + i + ')' + suffix);
        }
        file.transferTo(newFile);
        solrPost(fileDir);
        return 1;
    }

    public int solrPost(String fileDir) {
        try {
            Process process = Runtime.getRuntime().exec("/home/stephen/script/solr_post.sh " + fileDir);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
