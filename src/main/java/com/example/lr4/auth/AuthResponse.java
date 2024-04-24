package com.example.lr4.auth;

import com.example.lr4.news.News;
import com.example.lr4.session.Session;
import com.example.lr4.user.User;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

//public class AuthResponse {
//    public User user;
//    public Session session;
//
//    AuthResponse(User user, Session session) {
//        this.user = user;
//        this.session = session;
//    }
//}


public class AuthResponse {
    public String userId;
    public String name;
    public String surname;
    public String username;
    public String password;
    public int role;
    public Binary image;

    public String sessionId;
    public String session;
    public String user;
    public List<String> favoriteNews;


    AuthResponse(User user, Session session) {
        userId = user.id;
        name = user.name;
        surname = user.surname;
        username = user.username;
        password = user.password;
        role = user.role;
        sessionId = session.id;
        this.session = session.session;
        this.user = session.user;
        this.image = user.image;
        this.favoriteNews = user.favoriteNews;
    }

//    AuthResponse(User user, Session session) {
//        this.user = user;
//        this.session = session;
//    }
}
