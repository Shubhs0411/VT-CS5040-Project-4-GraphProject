/**
 * Node class representing a node in a linked list.
 * Each node contains data and a reference to the next node in the list.
 *
 * @author Shubham Laxmikant Deshmukh
 * @version v1
 */
public class Node {
    private String data;
    private Node next;

    /**
     * Constructor for creating a Node with default values.
     */
    public Node() {
        this.data = null;
        this.next = null;
    }


    /**
     * Get the data stored in the node.
     *
     * @return The data in the node
     */
    public String getData() {
        return data;
    }


    /**
     * Set the data for the node.
     *
     * @param data
     *            The data to set in the node
     */
    public void setData(String data) {
        this.data = data;
    }


    /**
     * Get the reference to the next node.
     *
     * @return The next node in the list
     */
    public Node getNext() {
        return next;
    }


    /**
     * Set the reference to the next node.
     *
     * @param next
     *            The next node in the list
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
