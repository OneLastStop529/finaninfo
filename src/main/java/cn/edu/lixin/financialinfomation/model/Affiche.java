package cn.edu.lixin.financialinfomation.model;

import cn.edu.lixin.financialinfomation.config.BaseEntity;
import cn.edu.lixin.financialinfomation.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.security.acl.LastOwnerException;
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
