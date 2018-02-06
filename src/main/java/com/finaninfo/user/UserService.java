package com.finaninfo.user;

import com.finaninfo.commands.UserCommand;
import com.finaninfo.converters.UserCommandToUser;
import com.finaninfo.converters.UserToUserCommand;
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

    @Autowired
    private UserCommandToUser userCommandToUser;

    @Autowired
    private UserToUserCommand userToUserCommand;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userReposiory.findByUsername(username);

        return user.orElse(null);
    }

    public void saveUser(User user) {
        userReposiory.save(user);
    }

    public UserCommand findUserCommandById(Long userId) {
        return userToUserCommand.convert(findById(userId));
    }

    private User findById(Long userId) {
        User user = userReposiory.findOne(userId);
       return user;
    }


    public List<User> findAllUser() {

        List<User> users = (List<User>) userReposiory.findAll();
        for(User user:users){
            System.out.println(user.getUsername());
        }
        return users;
    }
}