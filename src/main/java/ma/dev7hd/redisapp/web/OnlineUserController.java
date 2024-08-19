package ma.dev7hd.redisapp.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.redisapp.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/online-users")
public class OnlineUserController {

    private IAppService appService;

    @PostMapping("/connect/{userId}")
    public ResponseEntity<String> connectUser(@PathVariable String userId) {
        appService.userOnline(userId);
        return ResponseEntity.ok("User connected: " + userId);
    }

    @PostMapping("/disconnect/{userId}")
    public ResponseEntity<String> disconnectUser(@PathVariable String userId) {
        appService.userOffline(userId);
        return ResponseEntity.ok("User disconnected: " + userId);
    }

    @GetMapping
    public ResponseEntity<Set<Object>> getOnlineUsers() {
        Set<Object> onlineUsers = appService.getOnlineUsers();
        return ResponseEntity.ok(onlineUsers);
    }
}

