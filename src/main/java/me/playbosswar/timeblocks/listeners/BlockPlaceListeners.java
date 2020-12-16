package me.playbosswar.timeblocks.listeners;

import me.playbosswar.timeblocks.Main;
import me.playbosswar.timeblocks.PlayTimeAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class BlockPlaceListeners implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Material m = e.getBlock().getType();

        ArrayList<Material> usedMaterials = new ArrayList<>();
        Main.getPlugin().getConfig().getList("used_materials").forEach(mat -> usedMaterials.add(Material.valueOf((String) mat)));

        if (!usedMaterials.contains(m)) {
            return;
        }

        if(p.hasPermission("timeblock.bypass")) {
            return;
        }

        int playerPlayTime = PlayTimeAPI.getTotalPlayTime(p);
        int requiredTime = Main.getPlugin().getConfig().getInt("minutesBeforeUse");
        int totalRequiredTime = requiredTime - playerPlayTime;

        if (playerPlayTime < requiredTime) {
            e.setCancelled(true);

            int hours = totalRequiredTime / 60;
            int minutes = totalRequiredTime % 60;

            p.sendMessage(Main.getPlugin().getConfig().getString("message").replace("%time%", hours + "h" + minutes + "m" ));
        }
    }
}
