package ma.dev7hd.redisapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor
@Data
@Builder
public class UserSession implements Serializable {
    private String name;
    private String email;
    private LocalDate loginDate;
}
