package com.finaninfo.converters;

import com.finaninfo.commands.UserCommand;
import com.finaninfo.user.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserToUserCommand implements Converter<User, UserCommand>{


    @Override
    @Transactional
    public UserCommand convert(User user) {
        if(user == null){
            return null;
        }

        UserCommand userCommand = new UserCommand();

        userCommand.setUsername(user.getUsername());
        userCommand.setFullname(user.getFullname());
        userCommand.setBirthday(user.getBirthday());
        userCommand.setCity(user.getCity());
        userCommand.setEmail(user.getEmail());
        userCommand.setPhoneNo(user.getPhoneNo());
        userCommand.setBio(user.getBio());

        return  userCommand;
    }
}
