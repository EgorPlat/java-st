package com.example.lr4.auth;

import com.example.lr4.session.SessionService;
import com.example.lr4.user.User;
import com.example.lr4.user.UserService;
import com.mongodb.client.result.DeleteResult;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    MongoTemplate mongoTemplate;

    AuthService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    User login(@NotNull AuthDto dto) {
        User user = userService.getByUsername(dto.username);
        if (user == null) {
            return null;
        }

        if (Objects.equals(user.password, dto.password)) {
            return user;
        }

        return null;
    }

    User register(@NotNull User dto) {
        User user = userService.getByUsername(dto.username);
        if (user != null) {
            return null;
        }
        if (!validate(dto)) {
            return null;
        }

        return userService.create(dto);
    }

    boolean logout(@NotNull String sessionId) {
        DeleteResult result = sessionService.delete(sessionId);
        return result.wasAcknowledged();
    }

    private boolean validate(User dto) {
        return dto.username.length() >= 6 && dto.password.length() >= 6 && dto.name.length() >= 2 && dto.surname.length() >= 2;
    }
}
