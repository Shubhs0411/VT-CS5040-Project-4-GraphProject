import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 * The Parser class reads commands from a specified file and executes
 * corresponding operations on a HashWorld object. Each line in the file
 * represents a command that modifies or queries the HashWorld instance.
 * Supported commands include "insert," "remove," and "print." The Parser
 * initializes a HashWorld with a given initial hash size and processes
 * commands from the input file.
 *
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class Parser {

    // Instance variable to store the HashWorld object
    private HashWorld world;

    /**
     * Constructor for the Parser class.
     *
     * @param initHashSize
     *            Initial hash size for creating the HashWorld instance
     * @param commandFile
     *            File containing commands to be parsed and executed
     */
    public Parser(int initHashSize, String commandFile) {
        // Initialize the HashWorld with the provided initial hash size
        world = new HashWorld(initHashSize);
        // Begin parsing commands from the specified file
        beginParsingByLine(commandFile, world);
    }


    /**
     * Parses commands from a file and executes corresponding operations on the
     * HashWorld.
     *
     * @param filename
     *            File containing commands
     * @param hashworld
     *            HashWorld instance on which commands are executed
     * @return True if parsing and execution are successful, false otherwise
     */
    public boolean beginParsingByLine(String filename, HashWorld hashworld) {
        try {
            // Create a Scanner to read from the specified file
            Scanner sc = new Scanner(new File(filename));
            Scanner scancmd;

            // Iterate through each line in the file
            while (sc.hasNextLine()) {
                // Read the next line from the file
                String line = sc.nextLine();
                scancmd = new Scanner(line);

                // Extract the command type from the line
                String cmd = scancmd.next();
                
                String type;

                // Process different command types
                if (cmd.equals("insert")) {
                    // Parse and extract information for the "insert" command
                    scancmd.useDelimiter("<SEP>");
                    String artist = scancmd.next().trim();
                    String song = scancmd.next();
                    // Execute the "insert" command on the HashWorld
                    hashworld.insert(artist, song);
                }
                else if (cmd.equals("remove")) {
                    // Parse and extract information for the "remove" command
                    type = scancmd.next();
                    String token;
                    token = scancmd.nextLine().trim();
                    // Execute the "remove" command on the HashWorld
                    hashworld.remove(token, type);
                }
                else if (cmd.equals("print")) {
                    // Parse and extract information for the "print" command
                    type = scancmd.next();
                    // Execute the "print" command on the HashWorld
                    hashworld.print(type);
                }

            }

            // Close the file scanner
            sc.close();
            return true;
        }
        catch (IOException e) {
            // Handle IO exceptions, print the stack trace, and return false
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Gets the HashWorld instance.
     *
     * @return The HashWorld instance
     */
    public HashWorld getWorld() {
        return world;
    }
}
