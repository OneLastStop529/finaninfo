package cn.edu.lixin.financialinfomation.converters;

import cn.edu.lixin.financialinfomation.commands.UserCommand;
import cn.edu.lixin.financialinfomation.user.User;
import org.springframework.core.convert.converter.Converter;

import javax.transaction.Transactional;

public class UserCommandToUser implements Converter<UserCommand,User> {


    @Override
    @Transactional
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

        return user;
    }
}
