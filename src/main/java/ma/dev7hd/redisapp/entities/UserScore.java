package ma.dev7hd.redisapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor
@Data
public class UserScore implements Serializable {
    private String userId;
    private double score;
}
