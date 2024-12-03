package be.kingquest.spigotFriend.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FriendFight {
    public static void friendFight(Player player, String playerName) {
        Inventory inv = Bukkit.createInventory(null, 3*9, "Freunde");

        ItemStack Bed1 = new ItemStack(Material.RED_BED);
        ItemMeta metaBed1 = Bed1.getItemMeta();
        metaBed1.setDisplayName("§cBedWars");
        Bed1.setItemMeta(metaBed1);

        ItemStack Bed2 = new ItemStack(Material.BLUE_BED);
        ItemMeta metaBed2 = Bed2.getItemMeta();
        metaBed2.setDisplayName("§9BedWars");
        Bed2.setItemMeta(metaBed2);

        ItemStack Bed3 = new ItemStack(Material.LIME_BED);
        ItemMeta metaBed3 = Bed3.getItemMeta();
        metaBed3.setDisplayName("§aBedWars");
        Bed3.setItemMeta(metaBed3);

        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta metastick = stick.getItemMeta();
        metastick.setDisplayName("§eStickFight");
        stick.setItemMeta(metastick);

        ItemStack kit = new ItemStack(Material.SHIELD);
        ItemMeta metakit = kit.getItemMeta();
        metakit.setDisplayName("§bKit-PvP §8(§4Coming Soon§8)");
        kit.setItemMeta(metakit);

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
        inv.setItem(10, Bed1);
        inv.setItem(11, Bed2);
        inv.setItem(12, Bed3);
        inv.setItem(13, null);
        inv.setItem(14, stick);
        inv.setItem(15, null);
        inv.setItem(16, kit);
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
