package com.konstantinbulygin.tictactoexml.restapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import static com.konstantinbulygin.tictactoexml.util.GameUtil.JSON_EXTENSION;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.XML_EXTENSION;

@RestController
@CrossOrigin(origins = "*")
public class FileController {

    /**
     * to use this rest controller you have to use postman with POST METHOD
     * upload file to the root directory of the project
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> loadFile(@RequestParam("file") MultipartFile file) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains(XML_EXTENSION) || fileName.contains(JSON_EXTENSION)) {
            File convertFile = new File(fileName);
            try {
                if (convertFile.createNewFile()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
                    fileOutputStream.write(file.getBytes());
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>("File is uploaded", HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong file", HttpStatus.NOT_ACCEPTABLE);
    }

}
