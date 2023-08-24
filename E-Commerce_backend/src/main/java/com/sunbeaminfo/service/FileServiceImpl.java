package com.sunbeaminfo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        String randomUUID = UUID.randomUUID().toString();
        String fileName1= randomUUID.concat(name.substring(name.lastIndexOf(".")));


        String filePath = path + File.separator + fileName1;


        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));


        return fileName1;
    }
    
}
