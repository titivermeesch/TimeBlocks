package me.playbosswar.timeblocks.commands;

import me.playbosswar.timeblocks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("§bPlease use /tb reload to reload the plugin");
            return true;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            Main.getPlugin().reloadConfig();
            Main.getPlugin().saveConfig();
            sender.sendMessage("§aTimeBlocks config reloaded");
            return true;
        }
        return true;
    }
}
