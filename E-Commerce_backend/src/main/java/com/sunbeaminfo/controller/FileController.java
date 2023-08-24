package com.sunbeaminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeaminfo.DTO.FileResponseDTO;
import com.sunbeaminfo.service.FileService;


@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDTO> fileUpload(@RequestParam("image") MultipartFile image){
        
        String fileName =null;
        try {
            fileName = this.fileService.uploadImage(path,image);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponseDTO(null, "Image upload failed!!"),HttpStatus.INTERNAL_SERVER_ERROR);

        }
        

        return new ResponseEntity<>(new FileResponseDTO(fileName, "Image successfully uploaded!!"),HttpStatus.OK);
        
    }

}
