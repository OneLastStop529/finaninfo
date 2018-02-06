package com.finaninfo.model;

import com.finaninfo.config.BaseEntity;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.GregorianCalendar;

@Setter
@Getter
@Entity
public class News extends BaseEntity {
    private String title;
    private String content;
    private GregorianCalendar composeDate;
    private Long visitTime;

    @Lob
    private Byte[] image;


    public News() {
    }

    public News(String title, String content, GregorianCalendar composeDate, Long visitTime) {
        this.title = title;
        this.content = content;
        this.composeDate = composeDate;
        this.visitTime = visitTime;
    }
}
