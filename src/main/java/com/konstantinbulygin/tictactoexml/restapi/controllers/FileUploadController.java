package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.konstantinbulygin.tictactoexml.parser.GameParserJson;
import com.konstantinbulygin.tictactoexml.parser.GameParserXml;
import com.konstantinbulygin.tictactoexml.service.GameDocumentReader;
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
public class FileUploadController {

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
                if (convertFile.exists()) {
                    convertFile.delete();
                }
                if (convertFile.createNewFile()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
                    fileOutputStream.write(file.getBytes());
                    fileOutputStream.close();
                    if (fileName.contains(XML_EXTENSION)) {
                        System.out.println("===== Replay from XML file =====");
                        GameDocumentReader reader = new GameParserXml();
                        reader.readGameFile(convertFile.toString());
                    }
                    if (fileName.contains(JSON_EXTENSION)) {
                        System.out.println("===== Replay from XML file =====");
                        GameDocumentReader reader = new GameParserJson();
                        reader.readGameFile(convertFile.toString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>("File is uploaded", HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong file", HttpStatus.NOT_ACCEPTABLE);
    }

}
