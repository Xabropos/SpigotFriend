package be.kingquest.spigotFriend.friendListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static be.kingquest.spigotFriend.friendManager.OfflineFriendsAnfragen.offlineFriendsAnfragen;
import static be.kingquest.spigotFriend.friendManager.OnlineFriendsAnfragen.onlineFriendsAnfragen;
import static be.kingquest.spigotFriend.gui.FreundInfo.FreundInfo;
import static be.kingquest.spigotFriend.gui.FreundeAnfragen.FreundeAnfragen;

public class FreundeListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Sicherstellen, dass ein Spieler klickt und ein Item vorhanden ist
        if (event.getWhoClicked() instanceof Player && event.getCurrentItem() != null) {
            if(!event.getView().getTitle().equalsIgnoreCase("Freunde"))return;
            // Prüfe, ob der Klick im Shop-Inventar stattfindet und nicht im Spielerinventar
            if (event.getClickedInventory() == event.getWhoClicked().getInventory()) {
                event.setCancelled(true); // Verhindere, dass Spieler im eigenen Inventar kaufen
                return;
            }
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            FreundeClickListener(clickedItem, player, event);

        }
    }

    public static void FreundeClickListener(ItemStack clickedItem, Player player, InventoryClickEvent event) {
        // Überprüfe, ob das Item einen Namen hat
        if (clickedItem.hasItemMeta()) {
            ItemMeta meta = clickedItem.getItemMeta();
            if (meta.hasDisplayName()) {
                if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§aFreunde")){
                    event.setCancelled(true);
                    return;
                }else if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§bFreundschaftsanfragen")){
                    event.setCancelled(true);
                    FreundeAnfragen(player, onlineFriendsAnfragen(player), offlineFriendsAnfragen(player));
                    return;
                }else if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§dEinstellungen")){
                    event.setCancelled(true);
                    player.closeInventory();
                    return;
                }
                event.setCancelled(true);


                // Extrahiere den Namen des Freundes aus dem Display-Namen
                String friendName = meta.getDisplayName().replace("§a", "").replace("§c", ""); // Entferne Farbcodes

                // Hier kannst du den Namen des Freundes weiterverarbeiten
                processFriendClick(friendName, player);
            }
        }
    }

    public static void processFriendClick(String friendName, Player player) {

        FreundInfo(player, friendName);

    }
}
