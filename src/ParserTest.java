import student.TestCase;

/**
 * Test class for Parser.
 * 
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class ParserTest extends TestCase {

    /**
     * Test the "insert" command functionality.
     */
    public void testInsertCommand() {
        // Test the "insert" command functionality
        Parser parser = new Parser(5, "commands.txt");
        assertFalse(parser.beginParsingByLine(
            "insert<SEP>ArtistName<SEP>SongTitle\n", parser.getWorld()));
    }


    /**
     * Test the "remove" command functionality.
     */
    public void testRemoveCommand() {
        // Test the "remove" command functionality
        Parser parser = new Parser(5, "commands.txt");
        assertFalse(parser.beginParsingByLine("remove artist ArtistName\n",
            parser.getWorld()));
    }


    /**
     * Test the "print" command functionality.
     */
    public void testPrintCommand() {
        // Test the "print" command functionality
        Parser parser = new Parser(5, "commands.txt");
        assertFalse(parser.beginParsingByLine("print artist\n", parser
            .getWorld()));
    }
}
