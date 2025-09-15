package com.webapp.staffmanager.util.validator;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.exception.GeneralException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<FileValid, MultipartFile> {

    @Value("${application.upload.day-off-document.max-size}")
    private long maxSize;

    @Override
    public void initialize(FileValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        boolean result = true;
        if (file == null) {
            return result;
        }
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        Long fileSize = file.getSize();
        String mimeType = null;
        try(var is = file.getInputStream()) {
            Tika tika = new Tika();
            mimeType = tika.detect(is);
        } catch (IOException _) {
            throw new GeneralException(AppResponseStatus.APP_400);
        }
        
        if(!isValidSize(maxSize) || !isSupportedContentType(mimeType) || !isSupportedExtension(extension)){
            result = false;
        }
        return result;
    }

    private boolean isSupportedExtension(String extension) {
        return extension!=null 
        && (extension.equals("png") 
        || extension.equals("jpg") 
        || extension.equals("jpeg") 
        || extension.equals("pdf"));
    }
    
    private boolean isSupportedContentType(String contentType) {
        return contentType!=null 
        && (contentType.equals("image/png") 
        || contentType.equals("image/jpg") 
        || contentType.equals("image/jpeg") 
        || contentType.equals("application/pdf"));
    }

    private boolean isValidSize(long size) {
        return size <= maxSize;
    }

}
