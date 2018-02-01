package cn.edu.lixin.financialinfomation.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImagefile(Long newsId, MultipartFile file);
}
