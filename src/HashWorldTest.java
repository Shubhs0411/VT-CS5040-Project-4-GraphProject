import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import student.TestCase;

/**
 * Test class for HashWorld, covering insertion, printing, and removal
 * operations.
 * 
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class HashWorldTest extends TestCase {

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    /**
     * Set up the test environment by redirecting System.out to outContent.
     */
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }


    /**
     * Tear down the test environment by resetting System.out.
     */
    public void tearDown() {
        System.setOut(System.out);
    }


    /**
     * Test the insertion and printing of artists and songs.
     */
    public void testInsertAndPrint() {
        HashWorld hashWorld = new HashWorld(5);

        // Insert a song and artist
        hashWorld.insert("Artist1", "Song1");
        assertEquals("|Artist1| is added to the Artist database.\n"
            + "|Song1| is added to the Song database.", outContent.toString()
                .trim());

        // Print the artist and song databases
        outContent.reset();
        hashWorld.print("artist");
        assertEquals("0: |Artist1|\ntotal artists: 1", outContent.toString()
            .trim());

        outContent.reset();
        hashWorld.print("song");
        assertEquals("1: |Song1|\ntotal songs: 1", outContent.toString()
            .trim());
    }


    /**
     * Test the removal of artists and songs.
     * 
     * @throws IOException
     *             If an I/O error occurs during the test
     */
    public void testRemove() throws IOException {
        HashWorld hashWorld = new HashWorld(5);

        // Insert a song and artist
        hashWorld.insert("Song1", "Artist1");

        // Remove the artist
        outContent.reset();
        hashWorld.remove("artist", "Artist1");
        assertEquals("", outContent.toString().trim());

        // Attempt to remove a non-existing artist
        outContent.reset();
        hashWorld.remove("artist", "NonExistingArtist");
        assertEquals("", outContent.toString().trim());

        // Remove the song
        outContent.reset();
        hashWorld.remove("song", "Song1");
        assertEquals("", outContent.toString().trim());

        // Attempt to remove a non-existing song
        outContent.reset();
        hashWorld.remove("song", "NonExistingSong");
        assertEquals("", outContent.toString().trim());
    }


    /**
     * Test the insertion of a song when the artist does not exist but the song
     * exists.
     */
    public void testInsertSongWithExistingSong() {
        HashWorld hashWorld = new HashWorld(5);

        // Insert a song without the artist
        hashWorld.insert("NonExistingArtist", "ExistingSong");

        // Check the output
        assertEquals("|NonExistingArtist| is added to the Artist database.\n"
            + "|ExistingSong| is added to the Song database.", outContent
                .toString().trim()); // No print statement for the artist

    }
}
