package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.konstantinbulygin.tictactoexml.exceptions.WrongFileFormatException;
import com.konstantinbulygin.tictactoexml.model.restapi.CustomFileLoader;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/gameplay", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileUploadController {

    @Autowired
    CustomFileLoader customFileLoader;

    /**
     * to use this rest controller you have to use postman with POST METHOD
     * upload file to the root directory of the project
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> loadFile(@RequestParam("file") MultipartFile file) throws WrongFileFormatException {
        return customFileLoader.getObjectResponseEntity(file);
    }
}
