package com.finaninfo.repository;

import com.finaninfo.model.Affiche;
import com.finaninfo.user.User;
import com.finaninfo.user.UserRepository;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler(Affiche.class)
public class AfficheEventHandler {
    private final UserRepository users;


    public AfficheEventHandler(UserRepository users) {
        this.users = users;
    }

    public void addAfficheBeforeAuthenticated(Affiche affiche){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = users.findByUsername(username);

    }

}
