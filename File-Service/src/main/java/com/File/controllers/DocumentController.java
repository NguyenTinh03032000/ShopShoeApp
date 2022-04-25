package com.File.controllers;

import com.File.dto.MessageResponseDto;
import com.File.dto.UploadFileResponseDto;
import com.File.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("document")
public class DocumentController {

	@Autowired
    private DocumentService documneService;
    
	/**
	 * @note api upload file to server
	 * @param file
	 * @param UserId
	 * @param docType
	 * @return
	 */
    @PostMapping("/uploadFile")
    public UploadFileResponseDto uploadFile(@RequestParam("file") MultipartFile file, 
            @RequestParam("userId") Integer UserId,
            @RequestParam("docType") String docType) {
        String fileName = documneService.storeFile(file, UserId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponseDto(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    /**
     * 
     * @param imageName
     * @return 
     * @throws IOException
     * @note support load image browser
     */
    @GetMapping(value = "/loadFile/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@Validated @PathVariable(value = "imageName") String imageName) throws IOException {
        try {
        	File serverFile = new File("ShopShoe-Service/src/main/resources/upload/" + imageName);

        	return ResponseEntity.ok(Files.readAllBytes(serverFile.toPath()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponseDto("Error"));
		}
    }
}
