package be.kingquest.spigotFriend.friendListener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FightListener implements Listener {

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
            case RED_BED:
                p.sendMessage("Da kommt was!");
                event.setCancelled(true);
                break;
            case BLUE_BED:
                p.sendMessage("Da kommt was!");
                event.setCancelled(true);
                break;
            case LIME_BED:
                p.sendMessage("Da kommt was!");
                event.setCancelled(true);
                break;
            case STICK:
                p.sendMessage("Da kommt was!");
                event.setCancelled(true);
                break;
            case SHIELD:
                p.sendMessage("§4Coming Soon!");
                event.setCancelled(true);
                break;
        }
    }

    public static String extractPlayerName(String message) {
        // Bekannte Struktur des Strings
        String prefix = "Kämpfe gegen ";
        String suffix = " um Rum und Ehre";

        // Überprüfen, ob der String das bekannte Muster enthält
        if (message.startsWith(prefix) && message.endsWith(suffix)) {
            // Extrahiere den Spielernamen aus der Mitte
            return message.substring(prefix.length(), message.length() - suffix.length());
        }

        // Rückgabe null, wenn der String nicht dem Muster entspricht
        return null;
    }
}
