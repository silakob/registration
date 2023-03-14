package com.registration.registration.Validator;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileValidator {
    public static void isValid(MultipartFile file) throws IOException{
        String contentType = file.getContentType();
        boolean result = (contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg"));
        if (result == false) {
            throw new IOException("Only PNG, JPEG or JPG images are allowed");
        }
    }
}
