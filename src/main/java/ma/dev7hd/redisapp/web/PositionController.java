package ma.dev7hd.redisapp.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.redisapp.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/positions")
public class PositionController {

    private IAppService appService;

    @GetMapping("/position/{userId}")
    public ResponseEntity<Long> getUserPosition(@PathVariable String userId) {
        Long position = appService.getUserPosition(userId);
        return ResponseEntity.ok(position);
    }


}
