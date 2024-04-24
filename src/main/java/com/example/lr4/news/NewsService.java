package com.example.lr4.news;

import com.example.lr4.user.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final MongoTemplate mongoTemplate;

    NewsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    List<News> all() {
        return mongoTemplate.findAll(News.class);
    }

    News findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, News.class);
    }

    News add(News dto) {
        News user = new News(dto.title, dto.content);
        return mongoTemplate.insert(user, "news");
    }

    News update(News dto) {
        News news = findById(dto.id);
        if (news == null) {
            return null;
        }
        news.title = dto.title;
        news.content = dto.content;

        return mongoTemplate.save(news);
    }

    public DeleteResult delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Query queryT = new Query();
        Update update = new Update().pull("favoriteNews", id);
        mongoTemplate.updateMulti(queryT, update, User.class);
        return mongoTemplate.remove(query, News.class);
    }
}
