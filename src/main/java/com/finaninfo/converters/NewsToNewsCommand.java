package com.finaninfo.converters;

import com.finaninfo.commands.NewsCommand;
import com.finaninfo.model.News;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewsToNewsCommand implements Converter<News,NewsCommand>{
    @Override
    public NewsCommand convert(News news) {
        if (news == null) {
            return null;
        }
        NewsCommand newsCommand = new NewsCommand();
        newsCommand.setTitle(news.getTitle());
        newsCommand.setContent(news.getContent());
        newsCommand.setComposeDate(news.getComposeDate());

        newsCommand.setVisitTime(news.getVisitTime());
        newsCommand.setImage(news.getImage());

        return newsCommand;
    }
}
