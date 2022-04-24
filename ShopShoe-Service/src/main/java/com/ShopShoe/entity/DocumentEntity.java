package com.ShopShoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "documents")
@Data
public class DocumentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer documentId;
    
    @Column(name = "user_id")
    private Integer UserId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "document_type")
    private String documentType;
    
    @Column(name = "document_format")
    private String documentFormat;
    
    @Column(name = "upload_dir")
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

    public DocumentEntity(Integer documentId, Integer userId, String fileName, String documentType, String documentFormat, String uploadDir) {
        this.documentId = documentId;
        UserId = userId;
        this.fileName = fileName;
        this.documentType = documentType;
        this.documentFormat = documentFormat;
        this.uploadDir = uploadDir;
    }

    public DocumentEntity() {
    }
}
