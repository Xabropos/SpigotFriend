package be.kingquest.spigotFriend.friendListener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static be.kingquest.spigotFriend.database.MysqlVorgaenge.freundschaftsanfrageAblehnen;
import static be.kingquest.spigotFriend.database.MysqlVorgaenge.freundschaftsanfrageAngenommen;

public class freundschaftanfrageAnnehmenOderAblehnenListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Sicherstellen, dass ein Spieler klickt und ein Item vorhanden ist
        if (event.getWhoClicked() instanceof Player && event.getCurrentItem() != null) {
            String playername = extractPlayerName(event.getView().getTitle());
            if(playername == null){
                event.setCancelled(true);
                return;
            }
            // Prüfe, ob der Klick im Shop-Inventar stattfindet und nicht im Spielerinventar
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
                freundschaftsanfrageAngenommen(p, playername);
                event.setCancelled(true);
                break;
            case RED_WOOL:
                freundschaftsanfrageAblehnen(p, playername);
                event.setCancelled(true);
                break;
            case PLAYER_HEAD:
                event.setCancelled(true);
                break;
        }
    }

    public static String extractPlayerName(String message) {
        // Bekannte Struktur des Strings
        String prefix = "Freundschaftanfrage von ";
        String suffix = " Annehmen";

        // Überprüfen, ob der String das bekannte Muster enthält
        if (message.startsWith(prefix) && message.endsWith(suffix)) {
            // Extrahiere den Spielernamen aus der Mitte
            return message.substring(prefix.length(), message.length() - suffix.length());
        }

        // Rückgabe null, wenn der String nicht dem Muster entspricht
        return null;
    }
}
