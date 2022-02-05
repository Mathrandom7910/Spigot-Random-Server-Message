package me.just.randomservermsg.chat.command;

import me.just.randomservermsg.RandomServerMsg;
import me.just.randomservermsg.file.FileHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MsgStartCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileHandler.ConfigFile config = new FileHandler.ConfigFile();
        config.isEnabled = true;
        FileHandler.saveConfig(config);
        RandomServerMsg.startMsgs();
        sender.sendMessage("Started random chat");
        return true;
    }
}
