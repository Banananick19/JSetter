package banana;

import banana.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        for ( int i = 0;i<args.length;i++) {
            System.out.println(args[i]);
        }
        RunnerConfig runner_config = new RunnerConfig();
        switch (args[0]) {
            case "r":
                runner_config.runConfig(args[1]);
                break;
            case "m":
                runner_config.makeConfig(args[1], new String[]{args[2],});
                break;
            case "rem":
                runner_config.removeConfig(args[1]);
                break;
            case "apnd":
                runner_config.appendToConfig(args[1], new String[]{args[2]});
                break;
            case "rem_op":
                runner_config.removeOption(args[1], args[2]);
                break;
            case "ren_con":
                runner_config.renameConfig(args[1], args[2]);
                break;
        }
    }
}
