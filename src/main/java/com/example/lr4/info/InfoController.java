package com.example.lr4.info;

import com.example.lr4.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;

    @Autowired
    SessionService sessionService;

    @GetMapping
    ResponseEntity<Info> get(@RequestHeader String sessionId) {
        //try {
            //if (sessionService.findBySession(sessionId) == null) {
                //return ResponseEntity.badRequest().build();
            //}

            return ResponseEntity.ok(infoService.get());
        //} catch (Exception exception) {
        //    return ResponseEntity.internalServerError().build();
        //}
    }
}
