package lt.bit.java2.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/*
 * https://spring.io/guides/gs/uploading-files/
 */

@Controller
@RequestMapping("/mvc/upload")
public class FileUploadController {

    @Value("${images.upload}")
    String imagesPath;

    @GetMapping
    String uploadForm() {
        return "upload-file";
    }

    @PostMapping
    String uploading(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File dst = new File(this.imagesPath + "/" + UUID.randomUUID().toString());
        try {
            file.transferTo(dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload-file";
    }
}
