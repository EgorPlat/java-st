package com.example.lr4.news;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "news")
public class News {
    @Id
    public String id;
    public String title;
    public String content;

    News() {
        
    }
    News(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
