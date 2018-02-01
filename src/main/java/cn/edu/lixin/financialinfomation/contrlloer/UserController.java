package cn.edu.lixin.financialinfomation.contrlloer;

import cn.edu.lixin.financialinfomation.commands.UserCommand;
import cn.edu.lixin.financialinfomation.user.User;
import cn.edu.lixin.financialinfomation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String loginPage(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    @RequestMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

    @RequestMapping("/signup")
    public String signup(User user){
        return "signup";
    }

    @RequestMapping(value = "/signupSuccess" ,method = RequestMethod.POST)
    public String signupSuccess(User user, @RequestParam("username") String username, @RequestParam("password") String password){
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));

        user.setRole("ROLE_USER");
        userService.saveUser(user);
        return "redirect:/";
    }
//    TODO: page that user can edit his or her details profile
    @RequestMapping("/edit-profile")
    public String editProfiePage(Model model , Principal principal){
        User user = (User) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        UserCommand userCommand = userService.findUserCommandById(user.getId());
        model.addAttribute("userCommand",userCommand);
        return "edit-profile";
    }
//    TODO: page that admin can see a display a list of users
    @RequestMapping("/admin/users")
    public String getUsersPage(Model model){
        model.addAttribute("users",userService.findAllUser());

        return "admin/users";
    }
//    TODO: action that admin deletes a certain user
    public String deleteUsers(Model model){


        model.addAttribute("users",userService.findAllUser());
        return "redirect:/admin/users";
    }

//    TODO:
}
