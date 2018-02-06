package com.finaninfo.commands;


import com.finaninfo.config.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.GregorianCalendar;

@Getter
@Setter
@NoArgsConstructor
public class NewsCommand extends BaseEntity{
    private String title;
    private String content;
    private GregorianCalendar composeDate;
    private Long visitTime;

    private Byte[] image;
}
