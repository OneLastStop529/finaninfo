package cn.edu.lixin.financialinfomation.contrlloer;

import cn.edu.lixin.financialinfomation.service.ImageService;
import cn.edu.lixin.financialinfomation.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private NewsServiceImpl newsService;


}
