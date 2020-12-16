package me.playbosswar.timeblocks.listeners;

import me.playbosswar.timeblocks.Main;
import me.playbosswar.timeblocks.utils.PlayTime;
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

        long playerPlayTime = PlayTime.getCurrentPlayTimeSeconds(p.getUniqueId()) / 60;
        long requiredTime = Main.getPlugin().getConfig().getInt("minutesBeforeUse");
        long totalRequiredTime = requiredTime - playerPlayTime;

        if (playerPlayTime < requiredTime) {
            e.setCancelled(true);

            long hours = totalRequiredTime / 60;
            long minutes = totalRequiredTime % 60;

            p.sendMessage(Main.getPlugin().getConfig().getString("message").replace("%time%", hours + "h" + minutes + "m" ));
        }
    }
}
