package ma.dev7hd.redisapp.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.redisapp.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/scores")
public class UserScoreController {

    private IAppService appService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> addUserScore(@PathVariable String userId, @RequestParam double score) {
        appService.addUserScore(userId, score);
        return ResponseEntity.ok("Score added for user: " + userId);
    }

    @GetMapping("/ranking")
    public ResponseEntity<Set<Object>> getUserRanking() {
        Set<Object> ranking = appService.getUserRanking();
        return ResponseEntity.ok(ranking);
    }

    @GetMapping("/nearby.")
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> getUsersNearby(double longitude, double latitude, double radiusInKm) {
        return appService.getUsersNearby(longitude, latitude, radiusInKm);
    }
}

