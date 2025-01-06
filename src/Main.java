import mode.CommandMode;
import mode.RunningMode;
import mode.VisualMode;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        RunningMode mode;
        if(args.length > 2 && args[0].equals("visual")){
            mode = new VisualMode(args[1], Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]));
            mode.run();
        } else if (args.length > 0 && args[0].equals("command")){
            mode = new CommandMode();
            mode.run();
        } else {
            System.out.println("Wrong arguments: " + Arrays.toString(args));
        }
    }
}