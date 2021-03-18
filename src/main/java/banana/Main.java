package banana;

import banana.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        for ( int i = 0;i<args.length;i++) {
            System.out.println(args[i]);
        }
        RunnerConfig runner_config = new RunnerConfig();
        //runner_config.run_config("1");
        //runner_config.make_config("2", new String[]{"C:\\Users\\andre\\OneDrive\\Рабочий стол\\programs\\books\\59192_8f10f9c64c0b84b8fbb177e8da728b9d.pdf", "C:\\Users\\andre\\OneDrive\\Рабочий стол\\programs\\books\\800263_b5ee61913d823835c74e2cbd1b4b3382.pdf"});
        runner_config.rename_config("2", "DAS");
    }
}
