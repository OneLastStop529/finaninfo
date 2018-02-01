package cn.edu.lixin.financialinfomation.converters;

import cn.edu.lixin.financialinfomation.commands.UserCommand;
import cn.edu.lixin.financialinfomation.user.User;
import org.springframework.core.convert.converter.Converter;

import javax.transaction.Transactional;

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

        return  userCommand;
    }
}
