package me.just.randomservermsg.chat.command;

import me.just.randomservermsg.file.FileHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MsgDurCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("Must input delay!");
            return false;
        }

        long duration = Long.parseLong(args[0]);
        FileHandler.ConfigFile config = new FileHandler.ConfigFile();
        config.duration = duration;
        FileHandler.saveConfig(config);
        sender.sendMessage("Set duration to " + duration);
        return true;
    }
}
