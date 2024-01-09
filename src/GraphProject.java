// -------------------------------------------------------------------------

import java.io.IOException;

/**
 * Main for Graph project (CS3114/CS5040 Fall 2023 Project 4).
 * Usage: java GraphProject <init-hash-size> <command-file>
 *
 * @author {Shubham Laxmikant Deshmukh}
 * @version {v1}
 *
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

// -------------------------------------------------------------------------

public class GraphProject {

    // Class variables to store initial hash size and command file
    private static int initHashSize;
    private static String commandFile;

    /**
     * Main method to start the GraphProject program.
     *
     * @param args
     *            Command line parameters containing initial hash size and
     *            command file
     * @throws IOException
     *             If an I/O error occurs during command file reading
     */
    public static void main(String[] args) throws IOException {
        // This is the main method for the program.
        readingInput(args);
        // Create a new Parser instance with the provided initial hash size and
        // command file
        new Parser(initHashSize, commandFile);
    }


    /**
     * Reads and validates the input parameters from the command line.
     *
     * @param input
     *            Command line parameters
     * @return True if input parameters are valid, false otherwise
     */
    private static boolean readingInput(String[] input) {
        // Check if the number of input parameters is correct
        if (input.length != 2) {
            System.out.println("Please enter appropriate inputs");
            return false;
        }

        try {
            // Parse and assign the initial hash size from the first command
            // line argument
            initHashSize = Integer.parseInt(input[0]);
        }
        catch (NumberFormatException nfe) {
            // Handle the case where the first argument is not an integer
            System.out.println("The first argument must be an integer.");
            return false;
        }

        // Assign the command file from the second command line argument
        commandFile = input[1];

        return true;
    }
}
