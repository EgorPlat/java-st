package com.example.lr4.info;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "info")
public class Info {
    @Id
    public String id;
    public int count;
    public long datetime;

    void setNowDateTime() {
        datetime = new Date().getTime();
    }

    Info(int count) {
        this.count = count;
    }
}
