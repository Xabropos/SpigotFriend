package be.kingquest.spigotFriend.redis;

import be.kingquest.spigotFriend.SpigotFriend;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

public abstract class Manager {

    private RedisManager.RedisManagerConfig config;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Getter
    private static final HashMap<String, Manager> managers = new HashMap<>();
    private static int lowestPriority = 6;

    @Getter
    private int priority = 5;
    protected final SpigotFriend spigotFriend;
    protected final File file;

    public Manager(SpigotFriend spigotFriend, String fileName) {
        this.spigotFriend = spigotFriend;
        file = new File(spigotFriend.dataDirectory().toFile().getAbsolutePath(), fileName);
        registerManager(this);
    }

    public abstract void onLoad();

    public void onReload() {
        onLoad();
    }

    protected <T extends ManagerConfig> T getConfigJson(Class<T> managerConfig) {
        try {
            return gson.fromJson(Files.readString(file.toPath(), StandardCharsets.UTF_8), managerConfig);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(ManagerConfig managerConfig) {
        try {
            if(!file.exists()) {
                Files.createDirectories(file.getParentFile().toPath());
                Files.createFile(file.toPath());
            }
            final BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
            writer.write(gson.toJson(managerConfig));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPriority(int priority) {
        this.priority = priority;
        if (priority >= lowestPriority) lowestPriority = priority+1;
    }

    public static void registerManager(Manager m) {
        managers.put(m.getClass().getSimpleName().toLowerCase(), m);
    }

    public abstract void createFile();

    public abstract void readFile();

    public record ManagerResponse(Manager manager, boolean success, Exception exception, long time) {}

    public abstract static class ManagerConfig {

        public abstract void loadDefaults();

    }
}
