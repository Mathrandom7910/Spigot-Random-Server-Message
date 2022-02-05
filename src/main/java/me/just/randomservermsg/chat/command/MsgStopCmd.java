package me.just.randomservermsg.chat.command;

import me.just.randomservermsg.RandomServerMsg;
import me.just.randomservermsg.file.FileHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MsgStopCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileHandler.ConfigFile config = new FileHandler.ConfigFile();
        config.isEnabled = false;
        FileHandler.saveConfig(config);
        RandomServerMsg.stopMsgs();
        sender.sendMessage("Stopped random chat");
        return true;
    }
}
