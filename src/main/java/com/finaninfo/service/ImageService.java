package com.finaninfo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long newsId, MultipartFile file);
}
