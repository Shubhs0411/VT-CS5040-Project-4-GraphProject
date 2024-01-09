import java.io.IOException;
import student.TestCase;

/**
 * Test class for the Hash class.
 * 
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class HashTest extends TestCase {

    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Check out the sfold method.
     *
     * @throws Exception
     *             either an IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertEquals(Hash.h("a", 10000) == 97, true);
        assertEquals(Hash.h("b", 10000) == 98, true);
        assertEquals(Hash.h("aaaa", 10000) == 1873, true);
        assertEquals(Hash.h("aaab", 10000) == 9089, true);
        assertEquals(Hash.h("baaa", 10000) == 1874, true);
        assertEquals(Hash.h("aaaaaaa", 10000) == 3794, true);
        assertEquals(Hash.h("Long Lonesome Blues", 10000) == 4635, true);
        assertEquals(Hash.h("Long   Lonesome Blues", 10000) == 4159, true);
        assertEquals(Hash.h("long Lonesome Blues", 10000) == 4667, true);
    }


    /**
     * Test hashInsert and hashContain methods.
     *
     * @throws IOException
     *             If an I/O error occurs during the test
     */
    public void testHashInsertAndContain() throws IOException {
        Hash hashTable = new Hash(5, "TestTable");
        KVPair pair1 = new KVPair("key1", new Node());
        KVPair pair2 = new KVPair("key2", new Node());

        // Insert a key-value pair
        hashTable.hashInsert(pair1);

        // Check if the key is contained in the hash table
        assertNotNull(hashTable.hashContain("key1"));
        assertNull(hashTable.hashContain("key2"));

        // Insert another key-value pair
        hashTable.hashInsert(pair2);

        // Check if the second key is contained in the hash table
        assertNotNull(hashTable.hashContain("key2"));
    }


    /**
     * Test hashRemove method.
     *
     * @throws IOException
     *             If an I/O error occurs during the test
     */
    public void testHashRemove() throws IOException {
        Hash hashTable = new Hash(5, "TestTable");
        KVPair pair1 = new KVPair("key1", new Node());
        KVPair pair2 = new KVPair("key2", new Node());

        // Insert key-value pairs
        hashTable.hashInsert(pair1);
        hashTable.hashInsert(pair2);

        // Remove the first key
        hashTable.hashRemove("key1");

        // Check if the first key is no longer contained
        assertNull(hashTable.hashContain("key1"));

        // Check if the second key is still contained
        assertNotNull(hashTable.hashContain("key2"));
    }


    /**
     * Test hashPrint method.
     *
     * @throws IOException
     *             If an I/O error occurs during the test
     */
    public void testHashPrint1() throws IOException {
        Hash hashTable = new Hash(5, "TestTable");
        KVPair pair1 = new KVPair("key1", new Node());
        KVPair pair2 = new KVPair("key2", new Node());

        // Insert key-value pairs
        hashTable.hashInsert(pair1);
        hashTable.hashInsert(pair2);

        // Print the hash table
        String output = hashTable.hashPrint("TestType");

        // Check if the printed output contains relevant information
        assertTrue(output.contains("key1"));
        assertTrue(output.contains("key2"));
        assertFalse(output.contains("total TestType: 2"));
    }


    /**
     * Test hashResize method.
     */
    public void testHashResize() {
        Hash hashTable = new Hash(5, "TestTable");
        assertEquals(0, hashTable.getCurrSize());

        // Trigger hashResize by inserting more elements than the initial
        // capacity
        for (int i = 1; i <= 10; i++) {
            hashTable.hashInsert(new KVPair("Key" + i, new Node()));
        }

        // After resizing, the table should have doubled capacity
        assertEquals(10, hashTable.getCurrSize());
    }


    /**
     * Test hashContain method.
     */
    public void testHashContain() {
        Hash hashTable = new Hash(5, "TestTable");

        // Insert a key-value pair
        KVPair keyValue = new KVPair("TestKey", new Node());
        hashTable.hashInsert(keyValue);

        // Check if the key exists in the hash table
        KVPair result = hashTable.hashContain("TestKey");
        assertNotNull(result);
        assertEquals("TestKey", result.getKey());

        // Check with a non-existing key
        assertNull(hashTable.hashContain("NonExistingKey"));
    }


    /**
     * Test hashPrint method.
     */
    public void testHashPrint() {
        Hash hashTable = new Hash(5, "TestTable");

        // Insert a few key-value pairs
        hashTable.hashInsert(new KVPair("Key1", new Node()));
        hashTable.hashInsert(new KVPair("Key2", new Node()));
        hashTable.hashInsert(new KVPair("Key3", new Node()));

        // Test printing for "song" type
        String songOutput = hashTable.hashPrint("song");
        assertTrue(songOutput.contains("Key1") && songOutput.contains("Key2")
            && songOutput.contains("Key3"));
        assertTrue(songOutput.contains("total songs: 3"));

        // Test printing for "artist" type
        String artistOutput = hashTable.hashPrint("artist");
        assertTrue(artistOutput.contains("Key1") && artistOutput.contains(
            "Key2") && artistOutput.contains("Key3"));
        assertTrue(artistOutput.contains("total artists: 3"));
    }


    /**
     * Test hashRemove method.
     *
     * @throws IOException
     *             If an I/O error occurs during the test
     */
    public void testHashRemove1() throws IOException {
        Hash hashTable = new Hash(5, "TestTable");

        // Insert a key-value pair
        KVPair keyValue = new KVPair("TestKey", new Node());
        hashTable.hashInsert(keyValue);

        // Remove the key
        hashTable.hashRemove("TestKey");
        assertEquals(0, hashTable.getCurrSize());

// // Attempt to remove a non-existing key
// hashTable.hashRemove("NonExistingKey"); // Should not throw an exception
    }


    /**
     * Test hashInsert method.
     */
    public void testHashInsert() {
        Hash hashTable = new Hash(5, "TestTable");

        // Insert a key-value pair
        KVPair keyValue = new KVPair("TestKey", new Node());
        hashTable.hashInsert(keyValue);
        assertEquals(1, hashTable.getCurrSize());

        // Insert another key-value pair with the same key (should not increase
        // size)
        KVPair duplicateKey = new KVPair("TestKey", new Node());
        hashTable.hashInsert(duplicateKey);
        assertEquals(1, hashTable.getCurrSize());
    }


    /**
     * Test hashInsert method with various scenarios.
     */
    public void testHashInsert1() {
        // Example 1: Inserting a new key
        Hash hashTable1 = new Hash(5, "TestTable1");
        KVPair keyValue1 = new KVPair("TestKey1", new Node());
        hashTable1.hashInsert(keyValue1);
        assertEquals(1, hashTable1.getCurrSize());

        // Example 2: Inserting when resizing is needed
        Hash hashTable2 = new Hash(3, "TestTable2");
        KVPair keyValue2 = new KVPair("TestKey2", new Node());
        KVPair keyValue3 = new KVPair("TestKey3", new Node());
        hashTable2.hashInsert(keyValue2);
        hashTable2.hashInsert(keyValue3); // Triggering resize
        assertEquals(2, hashTable2.getCurrSize());

        // Example 3: Inserting when there are collisions
        Hash hashTable3 = new Hash(5, "TestTable3");
        KVPair keyValue4 = new KVPair("TestKey4", new Node());
        KVPair keyValue5 = new KVPair("TestKey5", new Node());
        KVPair keyValue6 = new KVPair("TestKey6", new Node());
        KVPair keyValue7 = new KVPair("TestKey7", new Node());
        hashTable3.hashInsert(keyValue4);
        hashTable3.hashInsert(keyValue5);
        hashTable3.hashInsert(keyValue6);
        hashTable3.hashInsert(keyValue7); // Collision will occur
        assertEquals(4, hashTable3.getCurrSize());
    }


    /**
     * Test hashInsert method with a different hash table size.
     */
    public void testHashInsert2() {
        // Inserting with different hash table size
        Hash hashTable4 = new Hash(7, "TestTable4");
        KVPair keyValue8 = new KVPair("TestKey8", new Node());
        KVPair keyValue9 = new KVPair("TestKey9", new Node());
        KVPair keyValue10 = new KVPair("TestKey10", new Node());
        hashTable4.hashInsert(keyValue8);
        hashTable4.hashInsert(keyValue9);
        hashTable4.hashInsert(keyValue10); // No resizing needed
        assertEquals(3, hashTable4.getCurrSize());
    }


    /**
     * Test tombstone functionality.
     */
    public void testTombstone() {
        // Creating a hash table with a small size for simplicity
        Hash hashTable = new Hash(5, "TestTable");
        KVPair keyValue = new KVPair("TestKey", new Node());

        // Inserting a key
        hashTable.hashInsert(keyValue);
        assertEquals(1, hashTable.getCurrSize());
        // Removing the key (setting tombstone to true)
        try {
            hashTable.hashRemove("TestKey");
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Attempting to insert the same key again
        KVPair newKeyValue = new KVPair("TestKey", new Node());
        hashTable.hashInsert(newKeyValue);

        // Since the key was removed (tombstone set to true), the insertion
        // should succeed
        assertEquals(1, hashTable.getCurrSize());
    }


    /**
     * Test Resize functionality.
     */
    public void testResize() {
        Hash hashTable4 = new Hash(5, "TestTable4");
        KVPair keyValue8 = new KVPair("TestKey8", new Node());
        KVPair keyValue9 = new KVPair("TestKey9", new Node());
        KVPair keyValue10 = new KVPair("TestKey10", new Node());
        hashTable4.hashInsert(keyValue8);
        hashTable4.hashInsert(keyValue9);
        hashTable4.hashInsert(keyValue10); // No resizing needed
        assertEquals(10, hashTable4.getTableSize());
    }
}
