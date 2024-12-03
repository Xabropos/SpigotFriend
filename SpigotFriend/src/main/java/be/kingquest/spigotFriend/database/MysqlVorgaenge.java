package be.kingquest.spigotFriend.database;

import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static be.kingquest.spigotFriend.database.Mysql.getConnection;
import static be.kingquest.spigotFriend.database.Mysql.getConnectionInfoPlayer;

public class MysqlVorgaenge {

    public static boolean istUser(String Username) {
        try {
            Connection connection = getConnectionInfoPlayer();
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            String query = "SELECT * FROM PlayerInfo WHERE Username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, Username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public static void freundschaftsanfrageAblehnen(Player p, String playerName) {
        try {
            Connection connection = getConnectionInfoPlayer();
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            String query = "DELETE FROM " + p.getName() + " WHERE Username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, playerName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

            }else{

            }
        }catch (Exception e){

        }
    }

    public static void freundschaftsanfrageAngenommen(Player p, String playerName) {
        try {
            Connection connection = getConnectionInfoPlayer();
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            String query = "UPDATE " + p.getName() + " SET Angenommen = true WHERE Username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, playerName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

            }else{

            }
            query = "UPDATE " + playerName + " SET Angenommen = true WHERE Username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, p.getName());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

            }else{

            }
        }catch (Exception e){

        }
    }

    public static void addPlayer(Player p, String playerName){
        String query = "SELECT COUNT(*) FROM " + p.getName() + " WHERE Username = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Setze den Playernamen in die Abfrage ein
            statement.setString(1, playerName);

            // Führe die Abfrage aus
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                }else{
                    freundschaftsanfrageAngenommen(p, playerName);
                    p.sendMessage("§eIhr seid jz befreundet");
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query1 = "SELECT COUNT(*) FROM " + playerName + " WHERE Username = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query1)) {

            // Setze den Playernamen in die Abfrage ein
            statement.setString(1, p.getName());

            // Führe die Abfrage aus
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    p.sendMessage("§cDu hast ihn bereits eine einladung gesendet");
                }else{
                    addHinzufuegen(p, playerName);
                    p.sendMessage("§eDu hast ihn eine einladung gesendet");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addHinzufuegen(Player p, String playerName){
        String query = "INSERT INTO " + playerName + " (Username, playeruuid, FreundeSeit, Angenommen) VALUES (?, ?, NULL, false)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Setze die Werte für die Platzhalter
            statement.setString(1, p.getName()); // Username
            statement.setString(2, p.getUniqueId().toString()); // playeruuid

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFriendFromDB(Player p, String playerName){

        String query = "DELETE FROM " + p.getName() + " WHERE Username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Spielername in die SQL-Abfrage setzen
            stmt.setString(1, playerName);

            // Abfrage ausführen
            stmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }

        String query1 = "DELETE FROM " + playerName + " WHERE Username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query1)) {

            // Spielername in die SQL-Abfrage setzen
            stmt.setString(1, p.getName());

            // Abfrage ausführen
            stmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
