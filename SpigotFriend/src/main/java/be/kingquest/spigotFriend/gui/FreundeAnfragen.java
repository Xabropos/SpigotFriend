package be.kingquest.spigotFriend.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FreundeAnfragen {

    public static Inventory inventoryAnfragen = Bukkit.createInventory(null, 54, "Freunde");

    public static void FreundeAnfragen(Player player, Set<String> online, Set<String> offline) {
        int alleFreundeZahl = offline.size() + online.size();
        int slotsPerPage = 24;
        int totalPages = alleFreundeZahl / slotsPerPage;

        if(alleFreundeZahl == 0){
            inventoryAnfragen = inv("Du hast keine offenen Anfragen", inventoryAnfragen);

            ItemStack KeineFreunde = new ItemStack(Material.PLAYER_HEAD);
            ItemMeta KeineFreundeMeta = KeineFreunde.getItemMeta();
            KeineFreundeMeta.setDisplayName("§4Du hast keine offenen Anfragen");
            KeineFreunde.setItemMeta(KeineFreundeMeta);

            inventoryAnfragen.setItem(22, KeineFreunde);

        }else if(alleFreundeZahl<25){
            inventoryAnfragen = inv("Deine offenen Freundschaftanfragen", inventoryAnfragen);
            for (String element : online) {
                ItemStack Player = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta skullMeta = (SkullMeta) Player.getItemMeta();

                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(element);
                skullMeta.setOwningPlayer(offlinePlayer);
                skullMeta.setDisplayName("§a" + element);
                Player.setItemMeta(skullMeta);

                inventoryAnfragen.addItem(Player);
            }

            for (String element : offline) {
                ItemStack Player = new ItemStack(Material.SKELETON_SKULL);
                ItemMeta skullMeta = Player.getItemMeta();
                skullMeta.setDisplayName("§c" + element);
                Player.setItemMeta(skullMeta);

                inventoryAnfragen.addItem(Player);
            }

        }else{
            List<ItemStack> alleFreundeItems = new ArrayList<>();

            for (String element : online) {
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();

                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(element);
                skullMeta.setOwningPlayer(offlinePlayer);
                skullMeta.setDisplayName("§a" + element);
                playerHead.setItemMeta(skullMeta);

                alleFreundeItems.add(playerHead);
            }

            // Danach die Offline-Freunde hinzufügen
            for (String element : offline) {
                ItemStack skeletonHead = new ItemStack(Material.SKELETON_SKULL, 1);
                ItemMeta skullMeta = skeletonHead.getItemMeta();
                skullMeta.setDisplayName("§c" + element);
                skeletonHead.setItemMeta(skullMeta);

                alleFreundeItems.add(skeletonHead);
            }

            for (int page = 0; page < totalPages; page++) {
                inventoryAnfragen = inv("Deine Anfragen auf Seite " + (page + 1), inventoryAnfragen);
                setNavigationItems(inventoryAnfragen, page + 1, totalPages);

                // Füge Freunde zur aktuellen Seite hinzu
                int startIndex = page * slotsPerPage;
                int endIndex = Math.min(startIndex + slotsPerPage, alleFreundeItems.size());

                for (int i = startIndex; i < endIndex; i++) {
                    String friendName = String.valueOf(alleFreundeItems.get(i));

                    // Überprüfe, ob der Freund online ist
                    ItemStack friendItem;
                    if (online.contains(friendName)) {
                        // Online: Spieler-Kopf
                        friendItem = new ItemStack(Material.PLAYER_HEAD, 1);
                        SkullMeta skullMeta = (SkullMeta) friendItem.getItemMeta();
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(friendName);
                        skullMeta.setOwningPlayer(offlinePlayer);
                        skullMeta.setDisplayName("§a" + friendName); // Grün für Online
                        friendItem.setItemMeta(skullMeta);
                    } else {
                        // Offline: Skelett-Schädel
                        friendItem = new ItemStack(Material.SKELETON_SKULL, 1);
                        ItemMeta itemMeta = friendItem.getItemMeta();
                        itemMeta.setDisplayName("§c" + friendName); // Rot für Offline
                        friendItem.setItemMeta(itemMeta);
                    }

                    // Füge das Item dem Inventar hinzu
                    inventoryAnfragen.addItem(friendItem);
                }
            }
        }


        player.openInventory(inventoryAnfragen);
    }

    public static Inventory inv(String seite, Inventory inventory){
        ItemStack Hintergrund = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta HintergrundMeta = Hintergrund.getItemMeta();
        HintergrundMeta.setDisplayName("§6");
        Hintergrund.setItemMeta(HintergrundMeta);

        ItemStack HintergrundGrun = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta HintergrundGrunMeta = HintergrundGrun.getItemMeta();
        HintergrundGrunMeta.setDisplayName("§6");
        HintergrundGrun.setItemMeta(HintergrundGrunMeta);

        ItemStack Freunde = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta FreundeMeta = Freunde.getItemMeta();
        FreundeMeta.setDisplayName("§dFreunde");
        Freunde.setItemMeta(FreundeMeta);

        ItemStack Freundschaftsanfragen = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta FreundschaftsanfragenMeta = Freundschaftsanfragen.getItemMeta();
        FreundschaftsanfragenMeta.setDisplayName("§bFreundschaftsanfragen");
        Freundschaftsanfragen.setItemMeta(FreundschaftsanfragenMeta);

        ItemStack Einstellungen = new ItemStack(Material.COMPARATOR);
        ItemMeta EinstellungenMeta = Einstellungen.getItemMeta();
        EinstellungenMeta.setDisplayName("§dEinstellungen");
        Einstellungen.setItemMeta(EinstellungenMeta);

        ItemStack FreundeMenu = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta FreundeMenuMeta = FreundeMenu.getItemMeta();
        FreundeMenuMeta.setDisplayName("§eFreunde");
        FreundeMenu.setItemMeta(FreundeMenuMeta);

        inventory.setItem(0, FreundeMenu);
        inventory.setItem(1, Hintergrund);
        inventory.setItem(2, Hintergrund);
        inventory.setItem(3, Hintergrund);
        inventory.setItem(4, Hintergrund);
        inventory.setItem(5, Hintergrund);
        inventory.setItem(6, Hintergrund);
        inventory.setItem(7, Hintergrund);
        inventory.setItem(8, Hintergrund);

        inventory.setItem(9, Hintergrund);
        inventory.setItem(10, Hintergrund);
        inventory.setItem(11, null);
        inventory.setItem(12, null);
        inventory.setItem(13, null);
        inventory.setItem(14, null);
        inventory.setItem(15, null);
        inventory.setItem(16, null);
        inventory.setItem(17, Hintergrund);

        inventory.setItem(18, Freundschaftsanfragen);
        inventory.setItem(19, HintergrundGrun);
        inventory.setItem(20, null);
        inventory.setItem(21, null);
        inventory.setItem(22, null);
        inventory.setItem(23, null);
        inventory.setItem(24, null);
        inventory.setItem(25, null);
        inventory.setItem(26, Hintergrund);

        inventory.setItem(27, Hintergrund);
        inventory.setItem(28, Hintergrund);
        inventory.setItem(29, null);
        inventory.setItem(30, null);
        inventory.setItem(31, null);
        inventory.setItem(32, null);
        inventory.setItem(33, null);
        inventory.setItem(34, null);
        inventory.setItem(35, Hintergrund);

        inventory.setItem(36, Einstellungen);
        inventory.setItem(37, Hintergrund);
        inventory.setItem(38, null);
        inventory.setItem(39, null);
        inventory.setItem(40, null);
        inventory.setItem(41, null);
        inventory.setItem(42, null);
        inventory.setItem(43, null);
        inventory.setItem(44, Hintergrund);

        inventory.setItem(45, Hintergrund);
        inventory.setItem(46, Hintergrund);
        inventory.setItem(47, Hintergrund);
        inventory.setItem(48, Hintergrund);
        inventory.setItem(49, Hintergrund);
        inventory.setItem(50, Hintergrund);
        inventory.setItem(51, Hintergrund);
        inventory.setItem(52, Hintergrund);
        inventory.setItem(53, Hintergrund);

        return inventory;
    }


    public static void setNavigationItems(Inventory inventory, int currentPage, int totalPages) {
        // Slot 52 und 53 für die Navigation reservieren
        ItemStack leftArrow = null;
        ItemStack rightArrow = null;
        ItemStack exclamation = null;

        // Exclamation Item (MHF_Exclamation)
        exclamation = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta exclamationMeta = (SkullMeta) exclamation.getItemMeta();
        exclamationMeta.setOwner("MHF_Exclamation");
        exclamationMeta.setDisplayName("§cSeite " + (currentPage + 1));
        exclamation.setItemMeta(exclamationMeta);

        // Navigation zur vorherigen Seite (MHF_ArrowLeft)
        if (currentPage > 0) {
            leftArrow = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta leftArrowMeta = (SkullMeta) leftArrow.getItemMeta();
            leftArrowMeta.setOwner("MHF_ArrowLeft");
            leftArrowMeta.setDisplayName("§aZurück zur Seite " + currentPage);
            leftArrow.setItemMeta(leftArrowMeta);
        }

        // Navigation zur nächsten Seite (MHF_ArrowRight)
        if (currentPage < totalPages - 1) {
            rightArrow = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta rightArrowMeta = (SkullMeta) rightArrow.getItemMeta();
            rightArrowMeta.setOwner("MHF_ArrowRight");
            rightArrowMeta.setDisplayName("§aWeiter zur Seite " + (currentPage + 2));
            rightArrow.setItemMeta(rightArrowMeta);
        }

        // Setze die Navigation-Items basierend auf der Position (erste, letzte, dazwischen)
        if (currentPage == 0) {
            // Erste Seite: Exclamation auf 52 und 53
            inventory.setItem(52, exclamation);
            inventory.setItem(53, exclamation);
        } else if (currentPage == totalPages - 1) {
            // Letzte Seite: Exclamation auf 52 und 53
            inventory.setItem(52, exclamation);
            inventory.setItem(53, exclamation);
        } else {
            // Zwischen den Seiten: Pfeile setzen
            inventory.setItem(52, leftArrow);
            inventory.setItem(53, rightArrow);
        }
    }
}
