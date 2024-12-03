package be.kingquest.spigotFriend;

import be.kingquest.spigotFriend.commands.MeineFreunde;
import be.kingquest.spigotFriend.commands.MeineFreundschaftanfragen;
import be.kingquest.spigotFriend.friendListener.*;
import be.kingquest.spigotFriend.listener.JoinListener;
import be.kingquest.spigotFriend.redis.RedisManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class SpigotFriend extends JavaPlugin {

    private static SpigotFriend instance;

    private RedisManager redisManager;

    private Path dataDirectory;

    private static final Map<Material, Map<String, Consumer<Player>>> itemActions = new HashMap<>();

    public void listeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new FriendDeleteListener(), this);
        pluginManager.registerEvents(new freundschaftanfrageAnnehmenOderAblehnenListener(), this);
        pluginManager.registerEvents(new FreundInfoListener(), this);
        pluginManager.registerEvents(new FreundeListener(), this);
        pluginManager.registerEvents(new FightListener(), this);
        pluginManager.registerEvents(new AnfragenListener(), this);
    }

    public void commands(){
        getCommand("freunde").setExecutor(new MeineFreunde());
        getCommand("friends").setExecutor(new MeineFreunde());
        getCommand("friend").setExecutor(new MeineFreunde());
        getCommand("freundschaftanfragen").setExecutor(new MeineFreundschaftanfragen());
        getCommand("friendrequest").setExecutor(new MeineFreundschaftanfragen());
    }

    public Path dataDirectory() {
        return dataDirectory;
    }
}
