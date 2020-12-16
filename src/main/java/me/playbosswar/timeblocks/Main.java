package me.playbosswar.timeblocks;

import me.playbosswar.timeblocks.commands.MainCommands;
import me.playbosswar.timeblocks.listeners.BlockPlaceListeners;
import me.playbosswar.timeblocks.listeners.JoinLeaveListeners;
import me.playbosswar.timeblocks.utils.FileUtils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {
    private static Plugin plugin;
    public static Map<UUID, Long> playTimes = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        FileUtils.createBaseFiles();
        registerEvents();
        getCommand("timeblocks").setExecutor(new MainCommands());
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
