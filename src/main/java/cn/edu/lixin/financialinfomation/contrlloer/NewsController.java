package cn.edu.lixin.financialinfomation.contrlloer;

import cn.edu.lixin.financialinfomation.model.News;
import cn.edu.lixin.financialinfomation.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class NewsController {
//    autowiring News
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news-main/{page}")
    public String newsMainPage(Model model, @PathVariable("page")int page){
//        return a pagebale news page to the thymeleaf template
        Pageable pageable = new PageRequest(page-1, 3);
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

    //TODO: page that admin drafts new news



    //TODO:page that admin edits news
    @RequestMapping("/admin/news-edit/{id}")
    public String newsEditPage(){

        return null;
    }

    //TODO:action that admin delete certain piece of news
    @RequestMapping("/admin/news-delete/{id}")
    public String newsDeleteAction(){

        return "admin/news";
    }
}
