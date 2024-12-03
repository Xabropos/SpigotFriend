package be.kingquest.spigotFriend.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FriendDelete {
    public static void deleteFriend(Player p, String playerName){
        Inventory inv = Bukkit.createInventory(null, 9, "Freunde");

        ItemStack annehmen = new ItemStack(Material.LIME_WOOL);
        ItemMeta metaannehmen = annehmen.getItemMeta();
        metaannehmen.setDisplayName("§aAbbrechen");
        annehmen.setItemMeta(metaannehmen);

        ItemStack ablehnen = new ItemStack(Material.RED_WOOL);
        ItemMeta metaablehnen = ablehnen.getItemMeta();
        metaablehnen.setDisplayName("§4" + p.getName() + " als Freund entfernen!");
        ablehnen.setItemMeta(metaablehnen);

        inv.setItem(0, annehmen);
        inv.setItem(1, annehmen);
        inv.setItem(2, annehmen);
        inv.setItem(3, annehmen);
        inv.setItem(4, ablehnen);
        inv.setItem(5, annehmen);
        inv.setItem(6, annehmen);
        inv.setItem(7, annehmen);
        inv.setItem(8, annehmen);

        p.openInventory(inv);
    }
}
