package com.example.lr4.news;

import com.example.lr4.session.SessionService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    SessionService sessionService;

    @GetMapping
    ResponseEntity<List<News>> all(@RequestHeader String sessionId) {
        try {
            if (sessionService.findBySession(sessionId) == null) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(newsService.all());
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    ResponseEntity<News> add(@RequestHeader String sessionId, @RequestBody News dto) {
        if (sessionService.findBySession(sessionId) == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(newsService.add(dto));
    }

    @PutMapping
    ResponseEntity<News> update(@RequestHeader String sessionId, @RequestBody News dto) {
        try {
            if (sessionService.findBySession(sessionId) == null) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(newsService.update(dto));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@RequestHeader String sessionId, @PathVariable("id") String id) {
        if (sessionService.findBySession(sessionId) == null) {
            return ResponseEntity.badRequest().build();
        }

        DeleteResult result = newsService.delete(id);
        return ResponseEntity.ok().body(result.wasAcknowledged());
    }
}
