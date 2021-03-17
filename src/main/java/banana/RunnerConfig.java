package banana;

import org.ini4j.*;
import java.io.*;

public class RunnerConfig {
    String default_config_path = "C:\\Users\\andre\\OneDrive\\Рабочий стол\\config.ini"
    public static void main(String args[]) {

        try {
            // Execute command
            String command = "cmd /c start calc";
            Process proc = Runtime.getRuntime().exec(command);

        } catch (IOException e) {
        }
    }
}