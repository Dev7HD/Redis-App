package ma.dev7hd.redisapp;

import ma.dev7hd.redisapp.entities.UserSession;
import ma.dev7hd.redisapp.services.IAppService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class RedisAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(IAppService appService) {
        return args -> Stream.of("user1", "user2", "user3", "user4").forEach(user -> {

            UserSession userSession = UserSession.builder()
                    .name(user)
                    .email(user+"@mail.com")
                    .loginDate(LocalDate.now())
                    .build();
            appService.createUserSession("id_"+user, userSession);

            Stream.of("Message 1", "Message 2", "Message 3", "Message 4").forEach(message -> appService.addMessage("id_"+user,message));

            Stream.of("Task 1", "Task 2", "Task 3", "Task 4").forEach(task -> appService.addTask("id_"+user, task));

            appService.addUserScore("id_"+user,Math.random() * 1000 + 500);

            if (Math.random() > 0.5) {
                appService.userOnline("id_" + user);
            } else {
                appService.userOffline("id_" + user);
            }
        });
    }

}
