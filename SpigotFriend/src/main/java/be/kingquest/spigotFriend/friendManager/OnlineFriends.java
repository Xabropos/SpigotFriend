package be.kingquest.spigotFriend.friendManager;

import be.kingquest.spigotFriend.database.Mysql;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class OnlineFriends {

    public static Set<String> OnlineFriends(Player p) {
        try (Jedis jedis = new Jedis("89.58.30.136", 6379)) {
            jedis.auth("default", "Q6yZcoqs1bXWcH6");
            Set<String> alleOnlinePlayers = jedis.smembers("onlinePlayers");

            Set<String> alleFreunde = new HashSet<>();
            String query = "SELECT Username FROM " + p.getName() + " WHERE Angenommen = true";

            Connection connection = Mysql.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                alleFreunde.add(resultSet.getString("Username"));
            }

            // Neues Set für die Spieler, die online und Freunde sind
            Set<String> onlineFreunde  = new HashSet<>(alleOnlinePlayers);
            onlineFreunde .retainAll(alleFreunde);

            return onlineFreunde ;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
