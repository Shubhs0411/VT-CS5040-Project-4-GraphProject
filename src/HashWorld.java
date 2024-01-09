import java.io.IOException;

/**
 * The HashWorld class represents a world containing two Hash instances, one for
 * artists and one for songs. It provides methods for inserting, removing, and
 * printing entries in both hash tables.
 *
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class HashWorld {

    // Instance variables to store Hash instances for artists and songs
    private Hash artistHash;
    private Hash songHash;

    /**
     * Constructor for the HashWorld class. Initializes Hash instances for
     * artists and songs.
     *
     * @param initHashSize
     *            Initial hash size for creating the Hash instances
     */
    public HashWorld(int initHashSize) {
        // Create Hash instances for artists and songs with the specified
        // initial hash size
        artistHash = new Hash(initHashSize, "Artist");
        songHash = new Hash(initHashSize, "Song");
    }


    /**
     * Inserts a new entry into both the artist and song hash tables. Prints a
     * message indicating the success of the operation.
     *
     * @param artist
     *            The artist of the song to be inserted
     * @param song
     *            The song to be inserted
     */
    public void insert(String artist, String song) {
        // Create nodes for the artist and song
        Node artistNode = new Node();
        KVPair artistKVP = new KVPair(artist, artistNode);

        Node songNode = new Node();
        KVPair songKVP = new KVPair(song, songNode);

        if (artistHash.hashContain(artist) == null && songHash.hashContain(
            song) == null) {
            // Insert into both hash tables if neither the artist nor the song
            // exists
            artistHash.hashInsert(artistKVP);
            System.out.println("|" + artist
                + "| is added to the Artist database.");

            songHash.hashInsert(songKVP);
            System.out.println("|" + song + "| is added to the Song database.");
        }
        else if (songHash.hashContain(song) == null && artistHash.hashContain(
            artist) != null) {
            // Insert only into the song hash table if the artist exists
            songHash.hashInsert(songKVP);
            System.out.println("|" + song + "| is added to the Song database.");
        }
        else if (artistHash.hashContain(artist) == null && songHash.hashContain(
            song) != null) {
            // Insert only into the artist hash table if the song exists
            artistHash.hashInsert(artistKVP);
            System.out.println("|" + artist
                + "| is added to the Artist database.");
        }
        // If both artist and song exist, no insertion is performed
    }


    /**
     * Prints the entries in either the artist or song hash table based on the
     * specified type.
     *
     * @param inputType
     *            The type of entries to print ("artist" or "song")
     */
    public void print(String inputType) {
        if (inputType.equals("artist")) {
            // Print the entries in the artist hash table
            System.out.println(artistHash.hashPrint(inputType));
        }
        else if (inputType.equals("song")) {
            // Print the entries in the song hash table
            System.out.println(songHash.hashPrint(inputType));
        }
    }


    /**
     * Removes an entry from either the artist or song hash table based on the
     * specified type. Prints a message indicating the success of the removal or
     * if the entry does not exist.
     *
     * @param key
     *            The key of the entry to be removed
     * @param type
     *            The type of entry to remove ("artist" or "song")
     * @throws IOException
     *             If an I/O error occurs during the removal process
     */
    public void remove(String key, String type) throws IOException {
        if (type.equals("artist")) {
            // Check if the artist exists in the hash table, remove if present
            if (artistHash.hashContain(key) == null) {
                System.out.println("|" + key
                    + "| does not exist in the Artist database.");
            }
            else {
                artistHash.hashRemove(key);
                System.out.println("|" + key
                    + "| is removed from the Artist database.");
            }
        }
        else if (type.equals("song")) {
            // Check if the song exists in the hash table, remove if present
            if (songHash.hashContain(key) == null) {
                System.out.println("|" + key
                    + "| does not exist in the Song database.");
            }
            else {
                songHash.hashRemove(key);
                System.out.println("|" + key
                    + "| is removed from the Song database.");
            }
        }
    }

}
