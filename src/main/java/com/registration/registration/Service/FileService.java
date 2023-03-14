package com.registration.registration.Service;

import com.registration.registration.Infrastructure.Models.FileInfo;
import com.registration.registration.Repository.FileRepository;
import com.registration.registration.Validator.FileValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
@Service
public class FileService {
    private final String secretKey = "507f191e810c19729de860ea";
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileInfo saveFile(MultipartFile file) throws IOException {
        FileValidator.isValid(file);
        String name = file.getOriginalFilename();
        String contentType = file.getContentType();
        String content = Base64.getEncoder().encodeToString(file.getBytes());

        // Encrypt the file content
        String encryptedContent = encrypt(content);
        FileInfo savedFile = fileRepository.save(new FileInfo(name, contentType, encryptedContent));
        return savedFile;
    }

    public Optional<FileInfo> getFile(String fileId) {
        Optional<FileInfo> optionalFile = fileRepository.findById(fileId);
        if(optionalFile.isPresent()){
            FileInfo file = optionalFile.get();
            file.setContent(decrypt(file.getContent()));
            return Optional.of(file);
        }
        return optionalFile;
    }

    private String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt file content.");
        }
    }

    private String decrypt(String encryptedInput) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedInput);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt file content.");
        }
    }
}
