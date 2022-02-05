package me.just.randomservermsg.chat;

import me.just.randomservermsg.RandomServerMsg;
import me.just.randomservermsg.file.FileHandler;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Random;

public class ChatUtils{
    public static String getRandomMsg() {
        try {
            ArrayList<String> msgs = FileHandler.getFileStr(RandomServerMsg.MSGS_PATH);

            return msgs.get(new Random().nextInt(msgs.size()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void broadcast(String msg, Plugin plugin) {
        plugin.getServer().broadcastMessage(msg);
    }
}
