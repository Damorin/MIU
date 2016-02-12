import forwardModel.ForwardModel;
import forwardModel.ForwardModelImpl;
import input.InputReader;
import input.InputReaderImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damien Anderson on 05/02/16.
 */
public class MiuApplication {

    private static InputReader inputReader;
    private static ForwardModel forwardModel;

    public static void main(String[] args) {
        inputReader = new InputReaderImpl();
        forwardModel = new ForwardModelImpl();

        while(true) {
            String input = inputReader.getInput();
            double startTime = System.currentTimeMillis();
            System.out.println(forwardModel.breadthFirstSearch(input));
            double endTime = System.currentTimeMillis();
            double duration = (endTime - startTime);
            System.out.println("BFS Search Time: " + duration);

            startTime = System.currentTimeMillis();
            System.out.println(forwardModel.depthLimitedDFS(input, 12));
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime);
            System.out.println("DFS Search Time: " + duration);
        }
    }
}
