package me.playbosswar.timeblocks.utils;

import me.playbosswar.timeblocks.Main;

import java.util.UUID;

public class PlayTime {
    public static long getCurrentPlayTimeSeconds(UUID playerUUID) {
        long startTime = Main.playTimes.get(playerUUID);
        long timePlayed = System.currentTimeMillis() - startTime;
        long secondsPlayed = timePlayed / 1000;
        long cachedSecondsFromPast = Main.cachedPlayTimes.get(playerUUID);

        return secondsPlayed + cachedSecondsFromPast;
    }
}
