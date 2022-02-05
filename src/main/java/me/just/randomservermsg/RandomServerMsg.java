package me.just.randomservermsg;

import me.just.randomservermsg.chat.ChatUtils;
import me.just.randomservermsg.chat.command.AddMsgCmd;
import me.just.randomservermsg.chat.command.MsgDurCmd;
import me.just.randomservermsg.chat.command.MsgStartCmd;
import me.just.randomservermsg.chat.command.MsgStopCmd;
import me.just.randomservermsg.file.FileHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public final class RandomServerMsg extends JavaPlugin {
    public static final String DEFAULT_PATH = "./ABSOLUTEZEROTMAN_PLUGINS/random_server_message";
    public static final String MSGS_PATH = DEFAULT_PATH + "/messages.txt";
    public static final String CONFIG_PATH = DEFAULT_PATH + "/config.json";
    public static boolean reset = false;


    public static long duration = 100;
    public static boolean isEnabled = true;

    @Override
    public void onEnable() {
        // Plugin startup logic
        FileHandler.init();

        startMsgs();

        getCommand("rndmMsgAdd").setExecutor(new AddMsgCmd());
        getCommand("rndmMsgDuration").setExecutor(new MsgDurCmd());
      //  getCommand("rndmMsgStop").setExecutor(new MsgStopCmd());
       // getCommand("rndmMsgStart").setExecutor(new MsgStartCmd());
    }

    public static void stopMsgs() {
        reset = true;
    }

    public static void startMsgs() {
        if(!isEnabled) return;
        scheduleTask(duration);
    }

    public static void scheduleTask(long period) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RandomServerMsg");

        BukkitScheduler scheduler = plugin.getServer().getScheduler();

        scheduler.scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(RandomServerMsg.reset) {
                    cancel();
                    RandomServerMsg.reset = false;
                    return;
                }

                String msg = ChatUtils.getRandomMsg();
                if(msg == null) {
                    ChatUtils.broadcast("Unable to get random message, " + MSGS_PATH + " file empty? Disabling", plugin);
                    stopMsgs();
                } else ChatUtils.broadcast(msg, plugin);
            }
        }, 0L, period);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
