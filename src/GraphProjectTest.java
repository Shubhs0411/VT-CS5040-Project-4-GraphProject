import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is designed to test the GraphProject.
 *
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class GraphProjectTest extends TestCase {

    /**
     * Read contents of a file into a string
     *
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Set up the tests that follow.
     */
    public void setUp() {
        // Nothing needed yet
    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        GraphProject it = new GraphProject();
        assertNotNull(it);
    }


    /**
     * Test for the main method of GraphProject.
     *
     * @throws IOException
     *             If an I/O error occurs during file reading
     */
    public void testmain() throws IOException {
        // Prepare command-line arguments
        String[] args = new String[2];
        args[0] = "10";
        args[1] = "P4sampleInput.txt";

        // Call the main method
        GraphProject.main(args);

        // Check the output against the reference output
        String output = systemOut().getHistory();
        String referenceOutput = readFile("P4sampleOutput.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test for invalid input length.
     *
     * @throws IOException
     *             If an I/O error occurs during file reading
     */
    public void testInvalidInputLength() throws IOException {
        // Prepare command-line arguments with invalid length
        String[] args = { "128" };

        // Call the main method
        GraphProject.main(args);

        // Check the output against the expected output for invalid input
        String output = systemOut().getHistory();
        assertFuzzyEquals("please enter appropriate inputs\r\n"
            + "when summers through does not exist in the song database\r\n"
            + "total songs 0\r\n" + "total artists 0\r\n"
            + "blind lemon jefferson is added to the artist database\r\n"
            + "long lonesome blues is added to the song database\r\n"
            + "long lonesome blues is added to the song database\r\n"
            + "long lonesome blues is added to the song database\r\n"
            + "ma rainey is added to the artist database\r\n"
            + "ma raineys black bottom is added to the song database\r\n"
            + "mississippi boweavil blues is added to the song database\r\n"
            + "song hash table size doubled\r\n"
            + "fixin to die blues is added to the song database\r\n"
            + "0 blind lemon jefferson\r\n" + "7 ma rainey\r\n"
            + "total artists 2\r\n" + "1 fixin to die blues\r\n"
            + "2 mississippi boweavil blues\r\n" + "7 long lonesome blues\r\n"
            + "15 long lonesome blues\r\n" + "16 ma raineys black bottom\r\n"
            + "19 long lonesome blues\r\n" + "total songs 6\r\n"
            + "sleepy does not exist in the song database\r\n"
            + "ma rainey does not exist in the artist database\r\n"
            + "ma rainey is removed from the artist database\r\n"
            + "0 blind lemon jefferson\r\n" + "7 tombstone\r\n"
            + "total artists 1", output);
    }
}
