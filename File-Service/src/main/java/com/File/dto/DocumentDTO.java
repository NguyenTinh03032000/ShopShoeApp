package com.File.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class DocumentDTO {

    private Integer documentId;

    private Integer UserId;

    private String fileName;

    private String documentType;

    private String documentFormat;
    
    private String uploadDir;

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public DocumentDTO(Integer documentId, Integer userId, String fileName, String documentType, String documentFormat, String uploadDir) {
        this.documentId = documentId;
        UserId = userId;
        this.fileName = fileName;
        this.documentType = documentType;
        this.documentFormat = documentFormat;
        this.uploadDir = uploadDir;
    }

    public DocumentDTO() {
    }
}
