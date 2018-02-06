package com.finaninfo.converters;

import com.finaninfo.commands.UserCommand;
import com.finaninfo.user.User;
import com.sun.istack.internal.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand,User> {


    @Override
    @Nullable
    @Synchronized
    public User convert(UserCommand userCommand) {
        if(userCommand == null){
        return null;
        }

        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setPassword(userCommand.getPassword());
        user.setEmail(userCommand.getEmail());
        user.setFullname(userCommand.getFullname());
        user.setPhoneNo(userCommand.getPhoneNo());
        user.setBirthday(userCommand.getBirthday());
        user.setCity(userCommand.getCity());
        user.setBio(userCommand.getBio());

        return user;
    }
}
