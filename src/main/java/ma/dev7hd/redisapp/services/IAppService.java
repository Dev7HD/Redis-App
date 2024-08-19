package ma.dev7hd.redisapp.services;

import ma.dev7hd.redisapp.entities.UserSession;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IAppService {
    //Créer une clé de session pour un utilisateur
    void createUserSession(String userId, UserSession session);

    //Mettre à jour l'email de l'utilisateur
    void updateUserEmail(String userId, String newEmail);

    //Récupérer toutes les informations de la session pour cet utilisateur
    Map<Object, Object> getUserSession(String userId);

    //Récupérer toutes les informations de la session pour cet utilisateur
    void deleteUserSession(String userId);

    //Utiliser une liste pour stocker les messages
    void addMessage(String userId, String message);

    //Récupérer les 3 derniers messages pour cet utilisateur
    List<Object> getLastMessages(String userId, int count);

    //Créer une liste pour stocker les tâches
    void addTask(String userId, String task);

    //Marquer une tâche comme complétée
    void completeTask(String userId, String task);

    //Récupérer toutes les tâches restantes
    List<Object> getAllTasks(String userId);

    //Utiliser un ensemble trié pour stocker les scores
    void addUserScore(String userId, double score);

    //Récupérer le classement des utilisateurs par score
    Set<Object> getUserRanking();

    //Récupérer la position d'un utilisateur spécifique
    Long getUserPosition(String userId);

    //Utiliser un ensemble pour stocker les utilisateurs en ligne
    void userOnline(String userId);

    //Retirer un utilisateur de l'ensemble lorsqu'il se déconnecte
    void userOffline(String userId);

    //Récupérer la liste des utilisateurs en ligne
    Set<Object> getOnlineUsers();

    //Stocker la position des utilisateurs
    void addUserLocation(String userId, double longitude, double latitude);

    //Rechercher les utilisateurs dans un rayon de 50 km autour d'une position donnée
    GeoResults<RedisGeoCommands.GeoLocation<Object>> getUsersNearby(double longitude, double latitude, double radiusInKm);
}
