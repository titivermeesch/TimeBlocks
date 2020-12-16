package me.playbosswar.timeblocks;

import me.playbosswar.timeblocks.commands.MainCommands;
import me.playbosswar.timeblocks.listeners.BlockPlaceListeners;
import me.playbosswar.timeblocks.listeners.JoinLeaveListeners;
import me.playbosswar.timeblocks.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {
    private static Plugin plugin;
    // Contains play times of current online players
    public static Map<UUID, Long> playTimes = new HashMap<>();
    // Contains cached times from online and offline players
    public static Map<UUID, Long> cachedPlayTimes = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        FileUtils.createBaseFiles();
        registerEvents();
        getCommand("timeblocks").setExecutor(new MainCommands());
        FileUtils.getSavedPlayTimes();

        for(Player p : Bukkit.getOnlinePlayers()) {
            Main.playTimes.put(p.getUniqueId(), System.currentTimeMillis());
        }
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new BlockPlaceListeners(), this);
        pm.registerEvents(new JoinLeaveListeners(), this);
    }
}
