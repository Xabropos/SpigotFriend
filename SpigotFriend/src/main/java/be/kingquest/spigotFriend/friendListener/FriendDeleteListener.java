package be.kingquest.spigotFriend.friendListener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static be.kingquest.spigotFriend.database.MysqlVorgaenge.deleteFriendFromDB;

public class FriendDeleteListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Sicherstellen, dass ein Spieler klickt und ein Item vorhanden ist
        if (event.getWhoClicked() instanceof Player && event.getCurrentItem() != null) {
            String playername = extractPlayerName(event.getView().getTitle());
            if(playername == null){
                event.setCancelled(true);
                return;
            }
            if (event.getClickedInventory() == event.getWhoClicked().getInventory()) {
                event.setCancelled(true); // Verhindere, dass Spieler im eigenen Inventar kaufen
                return;
            }

            Player player = (Player) event.getWhoClicked();
            Material mat = event.getCurrentItem().getType();

            // Ruf die Methode `BlöckeBlue` auf und übergib die relevanten Parameter
            items(mat, event, player, playername);
        }
    }

    public static void items(Material mat, InventoryClickEvent event, Player p, String playername) {
        switch (mat) {
            case LIME_WOOL:
                event.setCancelled(true);
                p.closeInventory();
                break;
            case RED_WOOL:
                event.setCancelled(true);
                p.closeInventory();
                deleteFriendFromDB(p, playername);
                break;
        }
    }

    public static String extractPlayerName(String message) {
        String prefix = "Möchtest du ";
        String suffix = " als freund entfernen?";

        // Überprüfen, ob die Nachricht nicht leer oder null ist
        if (message == null || message.isEmpty()) {
            return null;
        }

        // Überprüfen, ob die Nachricht mit dem Präfix beginnt und das Suffix enthält
        if (message.startsWith(prefix) && message.endsWith(suffix)) {
            // Extrahiere den Spielernamen zwischen Präfix und Suffix
            int start = prefix.length();
            int end = message.length() - suffix.length();
            String playerName = message.substring(start, end).trim();

            // Überprüfen, ob ein Spielername vorhanden ist
            if (!playerName.isEmpty()) {
                return playerName;
            }
        }

        // Rückgabe null, wenn das Muster nicht passt oder kein Spielername gefunden wurde
        return null;
    }
}
