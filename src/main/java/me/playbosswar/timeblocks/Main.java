package me.playbosswar.timeblocks;

import me.playbosswar.timeblocks.commands.MainCommands;
import me.playbosswar.timeblocks.listeners.BlockPlaceListeners;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
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
    }
}
