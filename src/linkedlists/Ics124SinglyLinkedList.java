package linkedlists;

public class Ics124SinglyLinkedList<T> implements Ics124List<T> {

    class SlNode {

        T x;
        SlNode next;
    };
    SlNode head; // start of sequence
    SlNode tail; // end of sequence
    int n; // length of the list/sequence

    public Ics124SinglyLinkedList() {
        head = null;
        tail = null;
        n = 0;
    }
    
    // My Helper Methods
    // Add function that adds to end of list, and only takes data to be added as parameter
    public void add(T x) {
        // Create new node, set value to x
        SlNode node = new SlNode();
        node.x = x;

        // Check if it is the first node to be added
        if (head == null) {
            head = node;
            tail = node; // tail should point to the same node as head
        } else { // else set current position of tail to reference new node, then set tail as the new node
            tail.next = node;
            tail = node;
        }
        n++; // increment size of list

    }
    
    // Used to help me visualize list
    public void printList() {
        int pos = 0;
        SlNode current = head;
        while (current != null) {
            System.out.println("Position: " + pos + ", Value: " + current.x);
            current = current.next;
            pos++;
        }

    }

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
    // Traverses list from start, finds and returns node at given index
    public SlNode getNode(int i) {
        SlNode node = head;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        return node;
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
    // If adding to index 0: sets newNode pointer to old head node (shifting it over), and then makes head newNode
    // Else: creates references to nodes before and after insertion point, and put the newNode inbetween them
    // Increments list size
    // Updates new position of tail node
    @Override
    public void add(int i, T x) {
        validateIndex(i, true);
        
        SlNode newNode = new SlNode();
        newNode.x = x;

        if (i == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            SlNode before = getNode(i-1);
            SlNode after = before.next;
            newNode.next = after;
            before.next = newNode;
        }

        n++;

        // Set tail to point to end of list node
        // This definetly isn't the most efficient but it made handling edge cases easy
        SlNode end = head;
        while (end.next != null) {
            end = end.next;
        }
        tail = end;
    }
    
    // Hard to explain my thought process so I'll use inline comments
    @Override
    public T remove(int i) {
        validateIndex(i);

        SlNode deleted;
        if (i == 0) { // if index 0
            if (n == 1) { // if last item
                deleted = head;
                head = null;
                tail = null;
            } else { // else delete head
                deleted = head;
                head = head.next;
            }
        } else { // for any other index
            SlNode previous = getNode(i-1); // get node before deletion point

            if (i == n - 1) { // if removing last item, set the tail to previous and delete
                deleted = tail; 
                tail = previous;
                previous.next = null;
            } else { // else adjust pointers to "skip over" deleted node
                deleted = previous.next;
                previous.next = deleted.next;
            }
        }
        
        n--; // decrement size
        
        return deleted.x;
    }
}
