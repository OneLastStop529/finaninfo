package com.finaninfo.service;

import com.finaninfo.model.News;
import com.finaninfo.repository.NewsRepository;
import com.finaninfo.user.User;
import com.finaninfo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private NewsRepository newsRepository;

    private UserRepository usersRepository;

    @Override
    @Transactional
    public void saveNewsImageFile(Long newsId, MultipartFile file) {
        News news = newsRepository.findOne(newsId);
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte eachByte :file.getBytes()){
                byteObjects[i] = eachByte;
                i++;
            news.setImage(byteObjects);
            newsRepository.save(news);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveProfilePicture(Long userId, MultipartFile file) {
        User user = usersRepository.findOne(userId);
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte eachByte :file.getBytes()){
                byteObjects[i] = eachByte;
                i++;
                user.setProfilePic(byteObjects);
                usersRepository.save(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
