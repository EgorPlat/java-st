package com.example.lr4.session;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class Session {
    @Id
    public String id;
    public String session;
    public String user;

    Session(String session, String user) {
        this.session = session;
        this.user = user;
    }
}
