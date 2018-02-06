package com.finaninfo.converters;

import com.finaninfo.commands.NewsCommand;
import com.finaninfo.model.News;
import com.sun.istack.internal.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewsCommandToNews implements Converter<NewsCommand,News>{

    @Override
    @Nullable
    @Synchronized
    public News convert(NewsCommand newsCommand) {
        if(newsCommand == null){
            return null;
        }

        News news = new News();
        news.setTitle(newsCommand.getTitle());
        news.setContent(newsCommand.getContent());
        news.setComposeDate(newsCommand.getComposeDate());
        news.setVisitTime(newsCommand.getVisitTime());


        return news;
    }
}
