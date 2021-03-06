package com.finaninfo.commands;


import com.finaninfo.config.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.GregorianCalendar;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand extends BaseEntity{

    private String username;
    private String password;
    private String fullname;
    private String role;
    private GregorianCalendar birthday;
    private Integer phoneNo;
    private String email;
    private String city;
    private String bio;



}
