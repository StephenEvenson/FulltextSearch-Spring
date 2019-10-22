package me.stephenj.administration.service;

import com.google.gson.Gson;
import me.stephenj.administration.model.Image;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangruntian
 */
@Service
public class ImageService {
    private final String PyTorch_REST_API_URL = "http://localhost:5000/hat_predict";
    private final String UPLOADED_FOLDER = "/home/stephen/images/";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private final String HADOOP_ADDR = "hdfs://localhost:9000";

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        try {
            byte[] bytes = file.getBytes();
            String fileName = dateFormat.format(new Date()) + file.getOriginalFilename();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            Files.write(path, bytes);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将本地文件上传到hdfs
     */
    public void copyLocalFile(String fileName) throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(HADOOP_ADDR), configuration, "root");
        fileSystem.copyFromLocalFile(new org.apache.hadoop.fs.Path("/home/stephen/images/" + fileName), new org.apache.hadoop.fs.Path("/root/original_img/" + fileName));
        fileSystem.close();
    }

    public void imageIdentification(String fileName) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("image", fileName);

        Map<String, String> mapj = new HashMap<String, String>();
        mapj.put("image", fileName);
        System.out.println("########### map :"  + mapj.toString());

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(mapj, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(PyTorch_REST_API_URL, httpEntity, String.class);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(responseEntity.getBody(), map.getClass());
//        String
        for (String[] person : (String [][])map.get("predictions") ) {
            System.out.println(person[0] + " 加上 " + person[1]);
        }
        System.out.println(responseEntity.getBody());
//        {"predictions":[["Dou","helmet",[175,32,391,290]]],"success":true}
        System.out.println("########### responseEntity all #############");
        System.out.println(responseEntity.getBody());
        System.out.println("################# end ######################");
    }

    public void copyHDFSFile() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(HADOOP_ADDR), configuration, "root");
        FileStatus[] listStatus = fileSystem.listStatus(new org.apache.hadoop.fs.Path("/root/handled_img/"));
        for (FileStatus status : listStatus) {
            if (status.isFile()) {
                fileSystem.copyToLocalFile(false, new org.apache.hadoop.fs.Path("/root/handled_img/" + status.getPath().getName()), new org.apache.hadoop.fs.Path("/home/stephen/ShowImages/" + status.getPath().getName()));
            }
        }
        fileSystem.close();
    }

    public void show(Image image) throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(HADOOP_ADDR), configuration, "root");
        FileStatus[] listStatus = fileSystem.listStatus(new org.apache.hadoop.fs.Path("/root/handled_img/"));
        for (FileStatus status : listStatus) {
            if (status.isFile() && status.getPath().getName().equals(image.getImage())) {
                fileSystem.copyToLocalFile(false, new org.apache.hadoop.fs.Path("/root/handled_img/" + status.getPath().getName()), new org.apache.hadoop.fs.Path("/home/stephen/ShowImages/" + status.getPath().getName()));
            }
        }
        fileSystem.close();
    }

}
