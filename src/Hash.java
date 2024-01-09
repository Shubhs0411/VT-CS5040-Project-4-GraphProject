import java.io.IOException;

/**
 * Hash table class representing a generic hash table with methods for
 * insertion,
 * resizing, checking existence, printing, and removal of key-value pairs.
 * The hash table uses quadratic probing for collision resolution and supports
 * resizing.
 * It also provides a hash function based on the given string key.
 *
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class Hash {

    // Constants and instance variables for the Hash class

    private int currSize;
    private int tableSize;
    private KVPair[] currHash;

    private String type;
    private String emptyName = "EMPTY";

    /**
     * Constructor for the Hash class. Initializes the hash table with the given
     * initial size.
     *
     * @param initHashSize
     *            Initial size of the hash table
     * @param type
     *            Title specifying the type of entries in the hash table
     */
    public Hash(int initHashSize, String type) {
        // Initialize instance variables and create a new hash table array
        currSize = 0;
        tableSize = initHashSize;
        this.type = type;
        currHash = new KVPair[initHashSize];

        // Initialize each slot in the hash table with an empty KVPair
        for (int i = 0; i < initHashSize; i++) {

            currHash[i] = new KVPair(emptyName);
        }
    }


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return The hash function value (the home slot in the table for this key)
     */

    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;

        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;

            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;

        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }


    /**
     * Inserts a key-value pair into the hash table using quadratic probing for
     * collision resolution.
     * Resizes the hash table if the load factor exceeds 0.5.
     *
     * @param input
     *            Key-value pair to be inserted
     */
    public void hashInsert(KVPair input) {

        if (input != null) {
            String key = input.getKey();

            if (currSize + 1 > (tableSize / 2)) {

                hashResize();
            }

            int position = h(key, tableSize);

            int homePos = h(key, tableSize);

            int jump = 1;

            while (true) {
                if (key.equals(currHash[position].getKey())
                    && !currHash[position].isTombstone()) {

                    return;
                }
                else if (!currHash[position].isTombstone() || emptyName.equals(
                    currHash[position].getKey())) {

                    break;
                }

                position = (homePos + (jump * jump)) % tableSize;
                jump++;
            }

            currHash[position] = input;
            input.setTombstone(false);
            currSize++;

        }

    }


    /**
     * Resizes the hash table to double its current capacity.
     */
    public void hashResize() {
        int newCapacity = tableSize * 2;
        Hash newHash = new Hash(newCapacity, type);

        for (KVPair record : currHash) {
            if (!record.isTombstone() && record.getKey() != emptyName) {
                newHash.hashInsert(record);
            }
        }

        currHash = newHash.currHash;
        tableSize = newHash.tableSize;
        System.out.println(type + " hash table size doubled.");
    }


    /**
     * Checks if the hash table contains a key and returns the corresponding
     * key-value pair.
     *
     * @param input
     *            Key to check for existence in the hash table
     * @return The key-value pair if found, null otherwise
     */
    public KVPair hashContain(String input) {
        if (currSize == 0) {
            return null;
        }

        int position = h(input, tableSize);

        int homePos = h(input, tableSize);

        int jump = 1;

        while (!(currHash[position].getKey().equals(emptyName))) {

            if (input.equals(currHash[position].getKey()) && !currHash[position]
                .isTombstone()) {

                return currHash[position];
            }
            else {

                position = (homePos + (jump * jump)) % tableSize;
                jump++;
            }
        }

        return null;
    }


    /**
     * Prints the entries in the hash table, including information about
     * tombstones.
     *
     * @param hashType
     *            Type of entries to print ("song" or "artist")
     * @return A formatted string containing the entries and additional
     *         information
     */
    public String hashPrint(String hashType) {
        String output = "";

        for (int i = 0; i < currHash.length; i++) {

            KVPair name = currHash[i];

            if (name.getKey() != emptyName) {

                output += (i) + ": ";

                if (name.isTombstone()) {

                    output += "TOMBSTONE\n";
                }
                else {
                    output += "|" + name.getKey() + "|" + "\n";
                }
            }
        }

        if (hashType.equals("song")) {

            output += "total " + "songs" + ": " + currSize;
        }
        else if (hashType.equals("artist")) {

            output += "total " + "artists" + ": " + currSize;
        }

        return output;
    }


    /**
     * Removes a key from the hash table by marking its corresponding key-value
     * pair as a tombstone.
     *
     * @param name
     *            Key to be removed from the hash table
     * @throws IOException
     *             If an I/O error occurs during the removal process
     */
    public void hashRemove(String name) throws IOException {
        KVPair foundName = hashContain(name);

        if (!foundName.isTombstone() && foundName != null) {
            foundName.setTombstone(true);
            currSize--;
        }
    }


    /**
     * Getter for the current size of the hash table.
     *
     * @return The current size of the hash table
     */
    public int getCurrSize() {

        return currSize;
    }


    /**
     * Getter for the size of the hash table.
     *
     * @return The size of the hash table
     */
    public int getTableSize() {

        return tableSize;
    }
}
