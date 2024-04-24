package com.example.lr4.info;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InfoService {

    private final MongoTemplate mongoTemplate;

    InfoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    Info get() {
        Info info = find();
        if (info == null) {
            info = create();
        }
        info.setNowDateTime();

        return info;
    }

    private Info find() {
        List<Info> infos = mongoTemplate.findAll(Info.class, "info");
        if (infos.size() > 0) {
            return infos.get(0);
        }

        return null;
    }


    private Info create() {
        Info info = new Info(0);
        return mongoTemplate.insert(info, "info");
    }

    public void increment() {
        Info info = get();
        info.count++;
        mongoTemplate.save(info);
    }
}
