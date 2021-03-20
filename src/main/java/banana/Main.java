package banana;

import banana.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        for ( int i = 0;i<args.length;i++) {
            System.out.println(args[i]);
        }
        RunnerConfig runner_config = new RunnerConfig();
        switch (args[0]) {
            case "r":
                runner_config.run_config(args[1]);
            case "m":
                runner_config.make_config(args[1], new String[]{args[2],});
            case "rem":
                runner_config.remove_config(args[1]);
            case "apnd":
                System.out.println("do");
                runner_config.append_to_config(args[1], new String[]{args[2]});
        }
    }
}
