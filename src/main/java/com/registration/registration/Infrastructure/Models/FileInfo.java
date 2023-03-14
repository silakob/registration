package com.registration.registration.Infrastructure.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "file")
public class FileInfo {
    @Id
    private String id;
    private String fileName;
    private String contentType;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String content;

    public FileInfo(String fileName, String contentType, String content) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
