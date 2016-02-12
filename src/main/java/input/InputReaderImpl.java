package input;

import java.util.Scanner;

/**
 * Created by Damien Anderson on 05/02/16.
 */
public class InputReaderImpl implements InputReader {

    private static final String QUIT = "QUIT";
    private Scanner inputScanner;

    public InputReaderImpl() {
        inputScanner = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        String input = inputScanner.next().toUpperCase().trim();

        if (input != QUIT) {
            return input;
        }
        inputScanner.close();
        return input;
    }
}
