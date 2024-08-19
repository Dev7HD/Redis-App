package ma.dev7hd.redisapp.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.redisapp.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/tasks")
public class TaskController {

    private IAppService appService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> addTask(@PathVariable String userId, @RequestBody String taskDescription) {
        appService.addTask(userId, taskDescription);
        return ResponseEntity.ok("Task added for user: " + userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> completeTask(@PathVariable String userId, @RequestBody String taskDescription) {
        appService.completeTask(userId, taskDescription);
        return ResponseEntity.ok("Task completed for user: " + userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Object>> getAllTasks(@PathVariable String userId) {
        List<Object> tasks = appService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }
}

