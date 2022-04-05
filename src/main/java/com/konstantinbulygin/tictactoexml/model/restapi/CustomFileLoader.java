package com.konstantinbulygin.tictactoexml.model.restapi;

import com.konstantinbulygin.tictactoexml.exceptions.WrongFileFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import static com.konstantinbulygin.tictactoexml.util.GameUtil.JSON_EXTENSION;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.XML_EXTENSION;

@Component
public class CustomFileLoader {

    public ResponseEntity<Object> getObjectResponseEntity(MultipartFile file) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (fileName.contains(XML_EXTENSION) || fileName.contains(JSON_EXTENSION)) {
            File convertFile = new File(fileName);
            try {
                if (convertFile.exists()) {
                    convertFile.delete();
                }
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
        return new ResponseEntity<>(new WrongFileFormatException("Wrong file format"), HttpStatus.NOT_ACCEPTABLE);
    }
}
