package me.just.randomservermsg.chat.command;

import me.just.randomservermsg.RandomServerMsg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;

public class AddMsgCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(args.length == 0) {
                sender.sendMessage("Cannot add blank message!");
                return false;
            }
            String rndMsg = String.join(" ", args);

            File msgsFile = new File(RandomServerMsg.MSGS_PATH);

            try {
                FileWriter writer = new FileWriter(msgsFile, true);
                writer.write(rndMsg);
                writer.flush();
                writer.close();
                sender.sendMessage("Added " + rndMsg);
                return true;
            } catch (Exception e) {
                sender.sendMessage("Error writing to file, deleted it?");
                e.printStackTrace();
                return false;
            }

    }
}
