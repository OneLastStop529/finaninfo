package com.finaninfo.model;

import com.finaninfo.config.BaseEntity;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Lob;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static com.fasterxml.jackson.databind.util.ISO8601Utils.format;

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

    public String getBriefContent(){
        return content.substring(0,50).concat("...");
    }

    public String getSimpleComposeDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = simpleDateFormat.format(composeDate.getTime());
        return date;
    }
}
