package com.finaninfo.contrlloer;

import com.finaninfo.commands.NewsCommand;
import com.finaninfo.service.ImageService;
import com.finaninfo.service.NewsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private NewsService newsService;



    @GetMapping("/admin/news/{id}/image")
    public String newsUploadForm(@PathVariable long id, Model model){
        model.addAttribute("news",newsService.findCommandById(id));
        return "";
    }

    @PostMapping("/admin/news/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveNewsImageFile(Long.valueOf(id), file);

        return "redirect:/admin/news-edit/" + id  ;
    }

    @GetMapping("news-details/{id}/newsimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        NewsCommand newsCommand = newsService.findCommandById(Long.valueOf(id));

        if (newsCommand.getImage() != null) {
            byte[] byteArray = new byte[newsCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : newsCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }


}
