package ma.dev7hd.redisapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLocation implements Serializable {
    private String userId;
    private double longitude;
    private double latitude;
}
