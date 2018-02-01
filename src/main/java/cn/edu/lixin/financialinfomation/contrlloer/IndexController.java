package cn.edu.lixin.financialinfomation.contrlloer;

import cn.edu.lixin.financialinfomation.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping({"/","/"})
    public String indexController(Model model, Principal principal){

        if(principal != null) {
            User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            model.addAttribute("user", user);
        }
        return "index";
    }

}
