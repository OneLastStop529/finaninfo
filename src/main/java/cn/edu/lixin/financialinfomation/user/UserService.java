package cn.edu.lixin.financialinfomation.user;

import cn.edu.lixin.financialinfomation.commands.UserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userReposiory;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userReposiory.findByUsername(username);

        return user.orElse(null);
    }

    public void saveUser(User user) {
        userReposiory.save(user);
    }

    public UserCommand findUserCommandById(Long userId) {
        return userReposiory.findCommandById(userId);
    }


    public List<User> findAllUser() {

        List<User> users = (List<User>) userReposiory.findAll();
        for(User user:users){
            System.out.println(user.getUsername());
        }
        return users;
    }
}