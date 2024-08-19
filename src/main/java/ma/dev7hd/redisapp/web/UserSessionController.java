package ma.dev7hd.redisapp.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.redisapp.entities.UserSession;
import ma.dev7hd.redisapp.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/sessions")
public class UserSessionController {

    private IAppService appService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> createUserSession(@PathVariable String userId, @RequestBody UserSession session) {
        appService.createUserSession(userId, session);
        return ResponseEntity.ok("Session created for user: " + userId);
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<String> updateUserEmail(@PathVariable String userId, @RequestParam String newEmail) {
        appService.updateUserEmail(userId, newEmail);
        return ResponseEntity.ok("Email updated for user: " + userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<Object, Object>> getUserSession(@PathVariable String userId) {
        Map<Object, Object> session = appService.getUserSession(userId);
        if (session != null && !session.isEmpty()) {
            return ResponseEntity.ok(session);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserSession(@PathVariable String userId) {
        appService.deleteUserSession(userId);
        return ResponseEntity.ok("Session deleted for user: " + userId);
    }
}