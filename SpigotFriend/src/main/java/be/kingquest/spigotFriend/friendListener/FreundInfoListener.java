package be.kingquest.spigotFriend.friendListener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Set;

import static be.kingquest.spigotFriend.friendManager.OnlineFriends.OnlineFriends;
import static be.kingquest.spigotFriend.gui.FriendDelete.deleteFriend;
import static be.kingquest.spigotFriend.gui.FriendFight.friendFight;

public class FreundInfoListener implements Listener {

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
            case DIAMOND_SWORD:
                event.setCancelled(true);
                p.closeInventory();
                Set<String> onlineFriends = OnlineFriends(p);
                if(onlineFriends.contains(playername)){
                    friendFight(p, playername);
                    return;
                }else {
                    p.sendMessage("§e" + playername + " ist Offline");
                }
                break;
            case BOOK:
                event.setCancelled(true);
                break;
            case RED_WOOL:
                event.setCancelled(true);
                p.closeInventory();
                deleteFriend(p, playername);
                break;
        }
    }

    public static String extractPlayerName(String message) {
        // Definiere das bekannte Präfix
        String prefix = "Freundschaftanfrage von ";

        // Überprüfen, ob die Nachricht nicht leer oder null ist
        if (message == null || message.isEmpty()) {
            return null;
        }

        // Überprüfen, ob die Nachricht mit dem Präfix beginnt
        if (message.startsWith(prefix)) {
            // Extrahiere den Spielernamen (alles nach dem Präfix)
            String playerName = message.substring(prefix.length()).trim();

            // Überprüfen, ob ein Spielername vorhanden ist
            if (!playerName.isEmpty()) {
                return playerName;
            }
        }

        // Rückgabe null, wenn das Muster nicht passt oder kein Spielername gefunden wurde
        return null;
    }
}
