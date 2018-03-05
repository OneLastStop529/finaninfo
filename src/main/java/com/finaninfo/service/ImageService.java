package com.finaninfo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveNewsImageFile(Long newsId, MultipartFile file);
    void saveProfilePicture(Long userId, MultipartFile file);

}
