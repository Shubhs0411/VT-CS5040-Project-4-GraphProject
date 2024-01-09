/**
 * KVPair class representing a key-value pair.
 * Each pair consists of a key, a corresponding linked list node (value), and a
 * tombstone flag.
 *
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class KVPair {

    private final String key;
    private final Node value;
    private boolean tombstone;

    /**
     * Constructor for creating a KVPair with a key, value (node), and tombstone
     * status.
     *
     * @param key
     *            The key of the pair
     * @param value
     *            The value (node) associated with the key
     */
    public KVPair(String key, Node value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Constructor for creating a KVPair with a key and a null value.
     *
     * @param key
     *            The key of the pair
     */
    public KVPair(String key) {
        this.key = key;
        this.value = null;
    }


    /**
     * Get the key of the pair.
     *
     * @return The key of the pair
     */
    public String getKey() {
        return key;
    }


    /**
     * Get the value (node) associated with the key.
     *
     * @return The value (node) of the pair
     */
    public Node getValue() {
        return value;
    }


    /**
     * Set the tombstone status of the pair.
     *
     * @param tombstone
     *            The tombstone status to set
     */
    public void setTombstone(boolean tombstone) {
        this.tombstone = tombstone;
    }


    /**
     * Check if the pair is marked as a tombstone.
     *
     * @return True if the pair is a tombstone, false otherwise
     */
    public boolean isTombstone() {
        return tombstone;
    }


    /**
     * String representation of the key.
     *
     * @return The string representation of the key
     */
    public String toString() {
        return key;
    }
}
