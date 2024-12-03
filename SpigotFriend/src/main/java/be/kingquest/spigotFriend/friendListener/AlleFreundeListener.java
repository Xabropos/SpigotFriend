package be.kingquest.spigotFriend.friendListener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.Set;

import static be.kingquest.spigotFriend.friendManager.OfflineFriends.offlineFriends;
import static be.kingquest.spigotFriend.friendManager.OfflineFriendsAnfragen.offlineFriendsAnfragen;
import static be.kingquest.spigotFriend.friendManager.OnlineFriends.OnlineFriends;
import static be.kingquest.spigotFriend.friendManager.OnlineFriendsAnfragen.onlineFriendsAnfragen;
import static be.kingquest.spigotFriend.gui.Freunde.FreundeGUI;
import static be.kingquest.spigotFriend.gui.FreundeAnfragen.FreundeAnfragen;
import static be.kingquest.spigotFriend.gui.FriendDelete.deleteFriend;
import static be.kingquest.spigotFriend.gui.FriendFight.friendFight;

public class AlleFreundeListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player) || event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        Material mat = clickedItem.getType();
        String displayName = clickedItem.getItemMeta() != null ? clickedItem.getItemMeta().getDisplayName() : "";

        // Suche nach einem passenden MenuItem

        event.setCancelled(true); // Verhindere weitere Aktionen
    }


    public static void FreundeClickListener(ItemStack clickedItem, Player player, InventoryClickEvent event, Material mat) {
        String displayName = clickedItem.getItemMeta() != null ? clickedItem.getItemMeta().getDisplayName() : "";

        // Überprüfe Material + Display-Name und führe spezifische Aktionen aus
        if (mat == Material.BLACK_STAINED_GLASS_PANE && displayName.equalsIgnoreCase("§6")) {
            player.sendMessage("Du hast auf den schwarzen Platzhalter geklickt.");
        } else if (mat == Material.LIME_STAINED_GLASS_PANE && displayName.equalsIgnoreCase("§6")) {
            player.sendMessage("Du hast auf den grünen Platzhalter geklickt.");
        } else if (mat == Material.PLAYER_HEAD && displayName.equalsIgnoreCase("§dFreunde")) {
            player.sendMessage("Du hast auf 'Freunde' geklickt!");
            // Beispiel: Neues Inventar öffnen
            openFriendsMenu(player);
        } else if (mat == Material.WRITABLE_BOOK && displayName.equalsIgnoreCase("§bFreundschaftsanfragen")) {
            player.sendMessage("Du hast auf 'Freundschaftsanfragen' geklickt!");
            // Beispiel: Freundschaftsanfragen anzeigen
            showFriendRequests(player);
        } else if (mat == Material.COMPARATOR && displayName.equalsIgnoreCase("§dEinstellungen")) {
            player.sendMessage("Du hast auf 'Einstellungen' geklickt!");
            // Beispiel: Einstellungen öffnen
            openSettingsMenu(player);
        } else {
            player.sendMessage("Unbekanntes Item wurde angeklickt!");
        }
    }

    private static void openFriendsMenu(Player player) {
        // Beispiel: Freunde-Menü öffnen
        player.sendMessage("Das Freunde-Menü wurde geöffnet.");
        // Logik zum Öffnen eines neuen Menüs hier einfügen
    }

    private static void showFriendRequests(Player player) {
        // Beispiel: Freundschaftsanfragen anzeigen
        player.sendMessage("Hier sind deine Freundschaftsanfragen!");
        // Logik für Freundschaftsanfragen hier einfügen
    }

    private static void openSettingsMenu(Player player) {
        // Beispiel: Einstellungen-Menü öffnen
        player.sendMessage("Einstellungen-Menü wird geöffnet.");
        // Logik zum Öffnen eines neuen Menüs hier einfügen
    }
}
