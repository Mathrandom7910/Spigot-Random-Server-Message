package me.just.randomservermsg.file;

import me.just.randomservermsg.RandomServerMsg;
import org.bukkit.plugin.java.JavaPlugin;

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
        File file = new File(RandomServerMsg.DEFAULT_PATH);

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
    }
}
