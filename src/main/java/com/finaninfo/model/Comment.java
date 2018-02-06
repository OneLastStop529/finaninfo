package com.finaninfo.model;


import com.finaninfo.config.BaseEntity;
import com.finaninfo.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Comment extends BaseEntity {
    @Size(max = 140)
    private String content;

    @ManyToOne
    private User user;

    private int likes;

    @ManyToOne
    private News news;

}
