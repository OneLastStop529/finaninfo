package com.finaninfo.model;

import com.finaninfo.config.BaseEntity;
import com.finaninfo.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.GregorianCalendar;
@Setter
@Getter
@Entity
public class Affiche extends BaseEntity {
    private String title;
    private String content;
    private GregorianCalendar uploadDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Affiche(String title, String content, GregorianCalendar uploadDate,User user) {
        this();
        this.title = title;
        this.content = content;
        this.uploadDate = uploadDate;
        this.user = user;
    }

    public Affiche() {
        super();
    }

}
