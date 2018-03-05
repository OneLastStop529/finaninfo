package com.finaninfo.contrlloer;

import com.finaninfo.commands.NewsCommand;
import com.finaninfo.model.News;
import com.finaninfo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewsController {
//    autowiring News
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news-main/{page}")
    public String newsMainPage(Model model, @PathVariable("page")int page){
//        return a pagebale news page to the thymeleaf template
        Pageable pageable = new PageRequest(page-1, 6);
        Page<News> newsByPage = newsService.getNewsByPage(pageable);
        model.addAttribute("newsSet", newsByPage);
        model.addAttribute("currentPage",page);
        return "news/index";
    }

    @RequestMapping(value = "/admin/news")
    public String newsAdminPage(Model model){
//        return a pagebale news page to the thymeleaf template

        Iterable<News> allNews = newsService.getAllNews();
        model.addAttribute("newsSet", allNews);
        return "admin/news";
    }

    @RequestMapping("/news-details/{id}")
    public String  newsDetailsPage(@PathVariable Long id, Model model){
       model.addAttribute("news", newsService.findNewsById(id));
        return "news/details";
    }

    @GetMapping("/admin/news-edit/{id}")
    public String newsEditPage(Model model, @PathVariable Long id){
        model.addAttribute("news",newsService.findCommandById(id));
        model.addAttribute("newsId",id);
        return "/admin/news-edit";
    }

    @PostMapping("/admin/news-edit/{id}")
    public String finishEditingPage(@ModelAttribute NewsCommand command){

        newsService.saveNewsCommand(command);
        return "/admin/news-list";
    }

    //TODO:action that admin delete certain piece of news
    @PostMapping("/admin/news-delete/{id}")
    public String newsDeleteAction(){

        return "admin/news";
    }
}
