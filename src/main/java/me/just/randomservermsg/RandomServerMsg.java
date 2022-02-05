package me.just.randomservermsg;

import me.just.randomservermsg.chat.ChatUtils;
import me.just.randomservermsg.file.FileHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class RandomServerMsg extends JavaPlugin {
    public static final String DEFAULT_PATH = "./ABSOLUTEZEROTMAN_PLUGINS/random_server_message/messages.txt";

    @Override
    public void onEnable() {
        // Plugin startup logic
        FileHandler.init();

        BukkitScheduler scheduler = getServer().getScheduler();

        JavaPlugin whyThisWayTho = this;

        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                String msg = ChatUtils.getRandomMsg();
                if(msg == null) {
                    ChatUtils.broadcast("Unable to get random message, " + DEFAULT_PATH + " file empty?", whyThisWayTho);
                    return;
                } else ChatUtils.broadcast(msg, whyThisWayTho);
            }
        }, 0L, 100L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
