package ma.dev7hd.redisapp.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.redisapp.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/messages")
public class MessageController {

    private IAppService appService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> addMessage(@PathVariable String userId, @RequestBody String messageContent) {
        appService.addMessage(userId, messageContent);
        return ResponseEntity.ok("Message added for user: " + userId);
    }

    @GetMapping("/{userId}/recent")
    public ResponseEntity<List<Object>> getRecentMessages(@PathVariable String userId) {
        List<Object> messages = appService.getLastMessages(userId, 3);
        return ResponseEntity.ok(messages);
    }
}