package be.kingquest.spigotFriend.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static be.kingquest.spigotFriend.database.Mysql.getConnectionInfoPlayer;
import static be.kingquest.spigotFriend.friendManager.OnlineFriends.OnlineFriends;

public class FreundInfo {

    public static void FreundInfo(Player p, String playerName) {
        String username = null;
        String playerUuid = null;
        String firstLogin = null;
        String lastLogout = null;
        String clanNamen = null;
        String rangClan = null;
        String rangServer = null;
        int klicks = 0;
        int plazBloecke = 0;
        int zerstoerteBloecke = 0;
        int online_ = 0;
        int onlineZeit = 0;
        int monsterGekillt = 0;
        int getoeteteSpieler = 0;
        int tode = 0;
        int aktuelleCoin = 0;
        int alleGesammelteCoins = 0;
        int nachrichten = 0;
        int getoetete = 0;
        int playerId = 0;
        int minigamesGespielt = 0;
        int minigamesGewonnen = 0;
        int minigamesVerloren = 0;
        int freunde = 0;
        int lvl = 0;
        int clanId = 0;
        String query = "SELECT * FROM PlayerInfo WHERE Username = ?";

        try (Connection conn = getConnectionInfoPlayer();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Spielername in die SQL-Abfrage einsetzen
            stmt.setString(1, playerName);

            // Abfrage ausführen und Ergebnisse abrufen
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                username = rs.getString("Username");
                playerUuid = rs.getString("playeruuid");
                firstLogin = rs.getString("FirstLogin");
                lastLogout = rs.getString("LastLogout");
                klicks = rs.getInt("Klicks");
                plazBloecke = rs.getInt("PlazBloecke");
                zerstoerteBloecke = rs.getInt("ZerstoerteBloecke");
                online_ = rs.getInt("Online");
                onlineZeit = rs.getInt("OnlineZeit");
                monsterGekillt = rs.getInt("MonsterGekillt");
                getoeteteSpieler = rs.getInt("GetoeteteSpieler");
                tode = rs.getInt("Tode");
                aktuelleCoin = rs.getInt("AktuelleCoin");
                alleGesammelteCoins = rs.getInt("AlleGesammelteCoins");
                nachrichten = rs.getInt("Nachrichten");
                getoetete = rs.getInt("Getoetete");
                playerId = rs.getInt("PlayerID");
                minigamesGespielt = rs.getInt("MinigamesGespielt");
                minigamesGewonnen = rs.getInt("MinigamesGewonnen");
                minigamesVerloren = rs.getInt("MinigamesVerloren");
                freunde = rs.getInt("Freunde");
                lvl = rs.getInt("lvl");
                clanNamen = rs.getString("ClanNamen");
                clanId = rs.getInt("ClanID");
                rangClan = rs.getString("RangClan");
                rangServer = rs.getString("RangServer");
            }

            Boolean online;
            Set<String> onlinePlayers = OnlineFriends(p);
            if (onlinePlayers.contains(playerName)) {
                online = true;
            } else {
                online = false;
            }

            Inventory inv = Bukkit.createInventory(null, 3 * 9, "Freunde");

            ItemStack fight = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta fightMeta = fight.getItemMeta();
            fightMeta.setDisplayName("§5Kämpfe gegen " + p.getName());
            fight.setItemMeta(fightMeta);


            ItemStack info = new ItemStack(Material.BOOK);
            ItemMeta infoMeta = info.getItemMeta();
            if (online) {
                infoMeta.setDisplayName("§7[§aOnline§7]§d " + playerName);
            } else {
                infoMeta.setDisplayName("§7[§4Offline§7]§d " + playerName);
            }

            List<String> lore = new ArrayList<>();
            if (online) {
            } else {
                lore.add("§5LastLogout: " + lastLogout);
            }
            lore.add("§5Rang: " + rangServer);
            if (clanNamen == null) {
            } else {
                lore.add("§5ClanNamen: " + clanNamen);
                lore.add("§5Clanrang: " + rangClan);
            }
            lore.add("§5Onlinezeit: " + onlineZeit);
            lore.add("§5Coins: " + aktuelleCoin);

            infoMeta.setLore(lore);
            info.setItemMeta(infoMeta);


            ItemStack kill = new ItemStack(Material.RED_WOOL);
            ItemMeta killMeta = kill.getItemMeta();
            killMeta.setDisplayName("§cFreundschaft mit " + p.getName() + " Beenden");
            kill.setItemMeta(killMeta);

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
            inv.setItem(10, fight);
            inv.setItem(11, null);
            inv.setItem(12, null);
            inv.setItem(13, info);
            inv.setItem(14, null);
            inv.setItem(15, null);
            inv.setItem(16, kill);
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

            p.openInventory(inv);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
