package com.example.lr4.auth;

import com.example.lr4.info.InfoService;
import com.example.lr4.session.Session;
import com.example.lr4.session.SessionService;
import com.example.lr4.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    InfoService infoService;
    @Autowired
    SessionService sessionService;

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(HttpServletRequest request, @RequestBody AuthDto dto) {
        try {
            User user = authService.login(dto);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            Session session = sessionService.create(request.getSession().getId(), user.id);
            if (session == null) {
                return ResponseEntity.internalServerError().build();
            }

            infoService.increment();

//            AuthResponse response = new AuthResponse(user, session);
            AuthResponse response = new AuthResponse(user, session);
            return ResponseEntity.ok().body(response);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/register")
    ResponseEntity<User> register(@RequestBody User dto) {
        User user = authService.register(dto);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/logout/{sessionId}")
    ResponseEntity<Boolean> logout(@PathVariable("sessionId") String sessionId) {
        boolean result = authService.logout(sessionId);
        return ResponseEntity.ok().body(result);
    }
}
