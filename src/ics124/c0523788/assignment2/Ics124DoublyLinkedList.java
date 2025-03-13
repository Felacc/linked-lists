package ics124.c0523788.assignment2;

public class Ics124DoublyLinkedList<T> implements Ics124List<T> {

    class DlNode {

        T x;
        DlNode prev, next;
    };

    DlNode dummy;
    int n;

    public Ics124DoublyLinkedList() {
        dummy = new DlNode(); // weird little loopy node (always the head's previous node and tail's next node)
        dummy.prev = dummy; // tail
        dummy.next = dummy; // head
        n = 0;
    }

    // My Helper Methods
    // Validate Index:
    // Used at the start of methods that take an index as a parameter to throw an error if index is out of bounds
    // The boolean parameter addingNode is flagged true when validateIndex is being used in a method that can add nodes
    // this is because highest the valid index for those methods is n, for all other methods the highest valid index is n-1
    // If the method is used without the boolean parameter, it defaults to false
    public void validateIndex(int index) {
        validateIndex(index, false);
    }

    public void validateIndex(int index, boolean addingNode) {
        if (addingNode) {
            if (index > n) {
                throw new IndexOutOfBoundsException("Index " + index + " is out of bound for list of size " + n
                        + ". Highest writeable index: " + n + ".");
            }

            if (index < 0) {
                throw new IndexOutOfBoundsException("The given index is less than 0");
            }
        } else {
            if (index == 0 && n == 0) {
                throw new IndexOutOfBoundsException("The list is empty.");
            }

            if (index >= n) {
                throw new IndexOutOfBoundsException("Index " + index + " is out of bound for list of size " + n
                        + ". Highest readable index: " + (n - 1) + ".");
            }

            if (index < 0) {
                throw new IndexOutOfBoundsException("The given index is less than 0");
            }
        }
    }

    // Get Node:
    // Decides whether to traverse list from start or end based on where the index lies relative to the middle
    public DlNode getNode(int i) {
        int middleIndex = n / 2;
        DlNode node;

        if (i <= middleIndex) {
            // Traverse list from start
            node = dummy.next;
            for (int j = 0; j < i; j++) {
                node = node.next;
            }
        } else {
            // Traverse list from end
            node = dummy.prev;
            for (int j = n - 1; j > i; j--) {
                node = node.prev;
            }
        }

        return node;
    }

    // Add (to the end of list):
    // If it's the first node to be added to the list: sets the head and tail to that node
    // Else: adds node on to the end of the list
    // Increments list size
    public void add(T x) {
        DlNode newNode = new DlNode();
        newNode.x = x;

        if (dummy.next == dummy) {
            newNode.prev = dummy;
            newNode.next = dummy;
            dummy.next = newNode;
            dummy.prev = newNode;
        } else {
            newNode.next = dummy;
            newNode.prev = dummy.prev;
            dummy.prev.next = newNode;
            dummy.prev = newNode;
        }

        n++;
    }

    // Interface Method Implementations
    // Size:
    // Self-explanatory
    @Override
    public int size() {
        return n;
    }

    // Get:
    // Uses default validateIndex so the index is within range of i < n
    // Uses getNode to find the desired node and return that nodes stored value
    @Override
    public T get(int i) {
        validateIndex(i);

        return getNode(i).x;
    }

    // Set:
    // If the given index is equal to the size of the list: adds the node to th end of the list (as specified by assignment requirements)
    // Else: uses validateIndex with true so the index is within range of i <= n
    // and finds the node with getNode and sets the stored value to x
    @Override
    public void set(int i, T x) {
        validateIndex(i, true);

        if (i == n) {
            add(x);
        } else {
            getNode(i).x = x;
        }
    }

    // Add (at specified index):
    // Uses validateIndex with true so the index is within range of i <= n
    // If it's the first node to be added to the list: sets the head and tail to that node
    // Else: uses two temporary nodes (before and after) to adjust pointers and insert the new node
    // Increments list size
    @Override
    public void add(int i, T x) {
        validateIndex(i, true);

        DlNode newNode = new DlNode();
        newNode.x = x;

        if (dummy.next == dummy) {
            newNode.prev = dummy;
            newNode.next = dummy;
            dummy.next = newNode;
            dummy.prev = newNode;
        } else {
            DlNode before = dummy;
            for (int j = 0; j < i; j++) { // traverses to node just before insertion point
                before = before.next;
            }
            DlNode after = before.next;
            before.next = newNode;
            after.prev = newNode;
            newNode.next = after;
            newNode.prev = before;
        }

        n++;
    }

    // Remove:
    // Uses default validateIndex so the index is within range of i < n
    // Uses getNode to find the node to be removed
    // Adjusts pointers of surrounding nodes to "skip over" the removed node
    // Decrements list size
    // Returns value of removed node
    @Override
    public T remove(int i) {
        validateIndex(i);

        DlNode toRemove = getNode(i);

        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;

        n--;

        return toRemove.x;

    }

}
