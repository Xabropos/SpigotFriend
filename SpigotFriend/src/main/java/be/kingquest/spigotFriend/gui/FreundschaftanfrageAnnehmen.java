package be.kingquest.spigotFriend.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class FreundschaftanfrageAnnehmen {

    public static void freundschaftanfrageAnnehmenOderAblehnen(Player player, String playerName) {
        Inventory inv = Bukkit.createInventory(null, 3*9, "Freunde");

        ItemStack annehmen = new ItemStack(Material.LIME_WOOL);
        ItemMeta metaannehmen = annehmen.getItemMeta();
        metaannehmen.setDisplayName("§aFreundschaftanfrage von " + player.getName() + " Annehmen");
        annehmen.setItemMeta(metaannehmen);

        ItemStack Player = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) Player.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
        skullMeta.setOwningPlayer(offlinePlayer);
        skullMeta.setDisplayName("§e" + playerName);
        Player.setItemMeta(skullMeta);

        ItemStack ablehnen = new ItemStack(Material.RED_WOOL);
        ItemMeta metaablehnen = ablehnen.getItemMeta();
        metaablehnen.setDisplayName("§cFreundschaftanfrage von " + player.getName() + " Ablehnen");
        ablehnen.setItemMeta(metaablehnen);

        inv.setItem(0, null);
        inv.setItem(1, null);
        inv.setItem(2, null);
        inv.setItem(3, null);
        inv.setItem(4, null);
        inv.setItem(5, null);
        inv.setItem(6, null);
        inv.setItem(7, null);
        inv.setItem(8, null);

        inv.setItem(9, null);
        inv.setItem(10, annehmen);
        inv.setItem(11, null);
        inv.setItem(12, null);
        inv.setItem(13, Player);
        inv.setItem(14, null);
        inv.setItem(15, null);
        inv.setItem(16, ablehnen);
        inv.setItem(17, null);

        inv.setItem(18, null);
        inv.setItem(19, null);
        inv.setItem(20, null);
        inv.setItem(21, null);
        inv.setItem(22, null);
        inv.setItem(23, null);
        inv.setItem(24, null);
        inv.setItem(25, null);
        inv.setItem(26, null);

        player.openInventory(inv);
    }
}
