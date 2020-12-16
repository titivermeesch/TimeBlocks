package me.playbosswar.timeblocks.utils;

import me.playbosswar.timeblocks.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class FileUtils {
    static String pluginFolderPath = Main.getPlugin().getDataFolder().getPath();
    static String timerFile = pluginFolderPath + "/times.json";

    public static Boolean fileExists() {
        File file = new File(timerFile);
        return file.exists();
    }

    public static void createBaseFiles() {
        if(fileExists()) {
            return;
        }

        try {
            FileWriter jsonFile = new FileWriter(timerFile);
            JSONObject defaultObject = new JSONObject();

            jsonFile.write(defaultObject.toJSONString());
            jsonFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePlayerTime(UUID playerUUID, long seconds) {
        try {
            Reader reader = new FileReader(timerFile);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            String uuid = playerUUID.toString();

            if(data.get(uuid) == null) {
                data.put(uuid, seconds);
            } else {
                long alreadyPlayedSeconds = (long) data.get(playerUUID.toString());
                long total = alreadyPlayedSeconds + seconds;

                data.replace(uuid, total);
            }

            FileWriter jsonFile = new FileWriter(timerFile);
            jsonFile.write(data.toJSONString());
            jsonFile.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static Map<UUID, Long> getSavedPlayTimes() {
        try {
            Reader reader = new FileReader(timerFile);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);

            for(Object key : data.keySet()) {
                String actualKey = (String) key;
                Main.cachedPlayTimes.put(UUID.fromString(actualKey), (Long) data.get(actualKey));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
