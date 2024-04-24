package com.example.lr4.session;

import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private final MongoTemplate mongoTemplate;

    SessionService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Session findBySession(String session) {
        if (session == null) {
            return null;
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("session").is(session));
        List<Session> sessions = mongoTemplate.find(query, Session.class);
        if (sessions.size() > 0) {
            return sessions.get(0);
        }

        return null;
    }

    public Session create(String sessionId, String userId) {
        if (sessionId == null) {
            return null;
        }

        Session session = new Session(sessionId, userId);
        return mongoTemplate.insert(session, "session");
    }

    public DeleteResult delete(String sessionId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("session").is(sessionId));
        return mongoTemplate.remove(query, Session.class);
    }
}
