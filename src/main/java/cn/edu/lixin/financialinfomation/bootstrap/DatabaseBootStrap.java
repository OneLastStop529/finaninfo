package cn.edu.lixin.financialinfomation.bootstrap;

import cn.edu.lixin.financialinfomation.model.Affiche;
import cn.edu.lixin.financialinfomation.repository.AfficheRepository;
import cn.edu.lixin.financialinfomation.model.News;
import cn.edu.lixin.financialinfomation.repository.NewsRepository;
import cn.edu.lixin.financialinfomation.user.User;
import cn.edu.lixin.financialinfomation.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class DatabaseBootStrap implements ApplicationRunner{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AfficheRepository afficheRepository;
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        Injecting preloaded user data into the database
        User user1 = new User("onelaststop", "12345ACd", "Hugo Hayes", "ROLE_USER");
        User user2 = new User("admin", "admin", "Administrator", "ROLE_ADMIN");
        List<User> users = Arrays.asList(
                user1,user2
        );
        userRepository.save(users);
        System.out.println(userRepository.findByUsername("onelaststop").get().getId());

//        Injecting preloaded affiche data into the database

        List<Affiche> affiches = Arrays.asList(
          new Affiche("This site sucks","It really sucks, my grandma can make a better web application than this",new GregorianCalendar(),user1)
        );
        afficheRepository.save(affiches);
//        Injecting preloaded affiche data into the database
        List<News> news = Arrays.asList(
                new News("村民合影排泄物","lorem ipsum sda pjpw joijqriqw quick ;lajfi joe pibw",new GregorianCalendar(),15L),
                new News("流浪小狗求助辅警","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus efficitur dolor in elit placerat aliquam. Praesent luctus, mi nec elementum viverra, neque est porta nibh, id aliquet quam ante at neque.",new GregorianCalendar(),15L),
                new News("46所高校将改名","lorem ipsum sda pjpw joijqriqw quick ;lajfi joe pibw",new GregorianCalendar(),15L),
                new News("做变性手术致重伤","lorem ipsum sda pjpw joijqriqw quick ;lajfi joe pibw",new GregorianCalendar(),15L),
                new News("I can't BELIEVE THIS","lorem ipsum sda pjpw joijqriqw quick ;lajfi joe pibw",new GregorianCalendar(),15L),
                new News("iPhoneX或停产","lorem ipsum sda pjpw joijqriqw quick ;lajfi joe pibw",new GregorianCalendar(),15L)
        );
        newsRepository.save(news);
    }
}
