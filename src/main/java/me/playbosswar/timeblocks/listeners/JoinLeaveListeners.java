package me.playbosswar.timeblocks.listeners;

import me.playbosswar.timeblocks.Main;
import me.playbosswar.timeblocks.utils.FileUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class JoinLeaveListeners implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Main.playTimes.put(p.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        UUID playerUUID = p.getUniqueId();
        long startTime = Main.playTimes.get(playerUUID);
        long timePlayed = System.currentTimeMillis() - startTime;
        long secondsPlayed = timePlayed / 1000;

        FileUtils.savePlayerTime(playerUUID, secondsPlayed);

        // Remove entry from main map
        Main.playTimes.remove(playerUUID);
        // Update cached times correctly so we don't need to re-read the main file
        if(Main.cachedPlayTimes.containsKey(playerUUID)) {
            secondsPlayed += Main.cachedPlayTimes.get(playerUUID);
        }

        Main.cachedPlayTimes.put(playerUUID, secondsPlayed);
    }
}
