package com.registration.registration.Controller;

import com.registration.registration.Infrastructure.Models.FileInfo;
import com.registration.registration.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("api/file")
public class FileController {
    private FileService service;
    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileInfo savedFile = service.saveFile(file);
        return ResponseEntity.ok(savedFile);
    }

    @GetMapping("download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) {
        Optional<FileInfo> optionalFile = service.getFile(fileId);

        if (optionalFile.isPresent()) {
            FileInfo file = optionalFile.get();
            byte[] decodeBase64 = Base64.getDecoder().decode(file.getContent());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFileName() + "\"")
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(decodeBase64);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
