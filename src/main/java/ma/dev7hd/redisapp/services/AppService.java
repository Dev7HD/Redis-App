package ma.dev7hd.redisapp.services;

import lombok.AllArgsConstructor;
import ma.dev7hd.redisapp.entities.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AppService implements IAppService {

    private RedisTemplate<String, Object> redisTemplate;

    //Créer une clé de session pour un utilisateur
    @Override
    public void createUserSession(String userId, UserSession session) {
        redisTemplate.opsForHash().put("session:" + userId, "name", session.getName());
        redisTemplate.opsForHash().put("session:" + userId, "email", session.getEmail());
        redisTemplate.opsForHash().put("session:" + userId, "loginDate", session.getLoginDate());
    }

    //Mettre à jour l'email de l'utilisateur
    @Override
    public void updateUserEmail(String userId, String newEmail) {
        redisTemplate.opsForHash().put("session:" + userId, "email", newEmail);
    }

    //Récupérer toutes les informations de la session pour cet utilisateur
    @Override
    public Map<Object, Object> getUserSession(String userId) {
        return redisTemplate.opsForHash().entries("session:" + userId);
    }

    //Supprimer toutes les informations de la session pour cet utilisateur
    @Override
    public void deleteUserSession(String userId) {
        redisTemplate.delete("session:" + userId);
    }

    //Utiliser une liste pour stocker les messages
    @Override
    public void addMessage(String userId, String message) {
        redisTemplate.opsForList().rightPush("messages:" + userId, message);
    }

    //Récupérer les 3 derniers messages pour cet utilisateur
    @Override
    public List<Object> getLastMessages(String userId, int count) {
        return redisTemplate.opsForList().range("messages:" + userId, -count, -1);
    }

    //Créer une liste pour stocker les tâches
    @Override
    public void addTask(String userId, String task) {
        redisTemplate.opsForList().rightPush("tasks:" + userId, task);
    }

    //Marquer une tâche comme complétée
    @Override
    public void completeTask(String userId, String task) {
        redisTemplate.opsForList().remove("tasks:" + userId, 1, task);
    }

    //Récupérer toutes les tâches restantes
    @Override
    public List<Object> getAllTasks(String userId) {
        return redisTemplate.opsForList().range("tasks:" + userId, 0, -1);
    }

    //Utiliser un ensemble trié pour stocker les scores
    @Override
    public void addUserScore(String userId, double score) {
        redisTemplate.opsForZSet().add("userScores", userId, score);
    }

    //Récupérer le classement des utilisateurs par score
    @Override
    public Set<Object> getUserRanking() {
        return redisTemplate.opsForZSet().reverseRange("userScores", 0, -1);
    }

    //Récupérer la position d'un utilisateur spécifique
    @Override
    public Long getUserPosition(String userId) {
        return redisTemplate.opsForZSet().reverseRank("userScores", userId);
    }

    //Utiliser un ensemble pour stocker les utilisateurs en ligne
    @Override
    public void userOnline(String userId) {
        redisTemplate.opsForSet().add("onlineUsers", userId);
    }

    //Retirer un utilisateur de l'ensemble lorsqu'il se déconnecte
    @Override
    public void userOffline(String userId) {
        redisTemplate.opsForSet().remove("onlineUsers", userId);
    }

    //Récupérer la liste des utilisateurs en ligne
    @Override
    public Set<Object> getOnlineUsers() {
        return redisTemplate.opsForSet().members("onlineUsers");
    }

    //Stocker la position des utilisateurs
    @Override
    public void addUserLocation(String userId, double longitude, double latitude) {
        redisTemplate.opsForGeo().add("userLocations", new Point(longitude, latitude), userId);
    }

    //Rechercher les utilisateurs dans un rayon de 50 km autour d'une position donnée
    @Override
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> getUsersNearby(double longitude, double latitude, double radiusInKm) {
        Circle circle = new Circle(new Point(longitude, latitude), new Distance(radiusInKm, Metrics.KILOMETERS));
        return redisTemplate.opsForGeo().radius("userLocations", circle);
    }

}
