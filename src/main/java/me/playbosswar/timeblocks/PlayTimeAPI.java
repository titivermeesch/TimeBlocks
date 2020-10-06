package me.playbosswar.timeblocks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PlayTimeAPI {
    public static int getTotalPlayTime(Player p) {
        List<World> worlds = Bukkit.getWorlds();
        JSONParser jsonParser = new JSONParser();

        int totalPlayMinutes = 0;
        for (World world : worlds) {
            world.save();
            try {

                File playerStatsFile = new File(world.getWorldFolder() + "/stats/" + p.getUniqueId().toString() + ".json");
                FileReader fr = new FileReader(playerStatsFile.getPath());
                JSONObject o = (JSONObject) jsonParser.parse(fr);

                JSONObject stats = (JSONObject) o.get("stats");
                JSONObject statsCustomData = (JSONObject) stats.get("minecraft:custom");

                long ticksPlayed = (long) statsCustomData.get("minecraft:play_one_minute");

                totalPlayMinutes += ticksPlayed / 1200;
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        }

        return totalPlayMinutes;
    }
}
