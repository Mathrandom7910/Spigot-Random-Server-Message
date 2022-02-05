package me.just.randomservermsg.file;

import com.google.gson.Gson;
import me.just.randomservermsg.RandomServerMsg;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static ArrayList<String> getFileStr(File file) throws IOException {
        ArrayList<String> texts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        for(String line; (line = reader.readLine()) != null;) {
            texts.add(line);
        }
        reader.close();
        return texts;
    }

    public static ArrayList<String> getFileStr(String filePath) throws IOException {
        return getFileStr(new File(filePath));
    }

    public static void init() {
        new File(RandomServerMsg.DEFAULT_PATH).mkdirs();
        File file = new File(RandomServerMsg.MSGS_PATH);

        if(!file.exists()) {
            try {
                file.createNewFile();


                PrintWriter printWriter = new PrintWriter(file);
                printWriter.println("Join the discord with /discord");
                printWriter.println("Make sure to vote with /vote");
                printWriter.println("Check a player's join date with /joindate");
                printWriter.flush();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            File configFile = new File(RandomServerMsg.CONFIG_PATH);
            if (!configFile.exists()) {
                configFile.createNewFile();

                PrintWriter cfgWriter = new PrintWriter(configFile);
                cfgWriter.println(new Gson().toJson(new ConfigFile()));
                cfgWriter.flush();
                cfgWriter.close();
            } else {
                ConfigFile cfg = new Gson().fromJson(new FileReader(configFile), ConfigFile.class);
                RandomServerMsg.duration = cfg.duration;
                RandomServerMsg.isEnabled = cfg.isEnabled;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig(ConfigFile config) {
        RandomServerMsg.isEnabled = config.isEnabled;;
        RandomServerMsg.duration = config.duration;
        try {
            PrintWriter cfgWriter;
            cfgWriter = new PrintWriter(RandomServerMsg.CONFIG_PATH);
            cfgWriter.println(new Gson().toJson(config));
            cfgWriter.flush();
            cfgWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static class ConfigFile {
        public long duration = RandomServerMsg.duration;
        public boolean isEnabled = RandomServerMsg.isEnabled;
    }
}
