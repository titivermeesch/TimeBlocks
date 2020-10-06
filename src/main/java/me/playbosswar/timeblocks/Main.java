package me.playbosswar.timeblocks;

import me.playbosswar.timeblocks.listeners.BlockPlaceListeners;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockPlaceListeners(), this);
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
