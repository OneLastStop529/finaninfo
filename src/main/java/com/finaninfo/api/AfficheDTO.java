package com.finaninfo.api;

import com.finaninfo.config.BaseEntity;
import com.finaninfo.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter
public class AfficheDTO extends BaseEntity{
    private String title;
    private String content;
    private Date uploaodDate;

    private User user;

}
