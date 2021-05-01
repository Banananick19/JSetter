package banana;

import java.io.*;

import java.util.Map;
import java.util.List;

import org.ini4j.ConfigParser;


public class ConfigsManager {

    protected static ConfigParser config = new ConfigParser();

    public static void main(String[] args) {
        //
    }


    public static void runConfig(String config_name) throws Exception {
        config.read(new File(Config.ConfigPath));
        for (String key : config.options(config_name)) {
            Runtime.getRuntime().exec("cmd /c start \"\" " + "\"" + config.get(config_name, key) + "\"");
        }

    }

    public static void makeConfig(String config_name, String[] apps) throws Exception {
        config.read(new File(Config.ConfigPath));
        config.addSection(config_name);

        for (int i = 0; i < apps.length; i++) {
            config.set(config_name, String.valueOf(i), apps[i]);
        }
        config.write(new File(Config.ConfigPath));
    }

    public static void appendToConfig(String config_name, String[] apps) throws Exception {
        int index = 0;
        config.read(new File(Config.ConfigPath));
        for (String key : config.options(config_name)) index++;


        for (String app : apps) {
            config.set(config_name, String.valueOf(index), app);
            index++;
        }
        config.write(new File(Config.ConfigPath));
    }

    public static void removeConfig(String config_name) throws Exception {
        config.read(new File(Config.ConfigPath));
        config.removeSection(config_name);
        config.write(new File(Config.ConfigPath));
    }

    public static void removeOption(String config_name, String option) throws Exception {
        config.read(new File(Config.ConfigPath));
        config.removeOption(config_name, option);
        config.write(new File(Config.ConfigPath));
    }

    public static void renameConfig(String old_config_name, String new_config_name) throws Exception {
        config.read(new File(Config.ConfigPath));
        config.addSection(new_config_name);
        for (String key : config.options(old_config_name)) {
            config.set(new_config_name, key, config.get(old_config_name, key));
        }
        removeConfig(old_config_name);
        config.write(new File(Config.ConfigPath));
    }

    public static List<String> getSections() throws Exception {
        config.read(new File(Config.ConfigPath));
        List<String> sections = config.sections();
        return sections;
    }

}