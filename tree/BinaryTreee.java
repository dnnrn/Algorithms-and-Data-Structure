
import java.util.Stack;

public class BinaryTreee {
    
    // Define the structure for a Node in the binary tree
    private class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            this.left = this.right = null;
        }
    }

    private Node root;

    // Constructor to create an empty binary tree
    public BinaryTreee() {
        root = null;
    }

    // Method to add nodes to the tree
    public void add(int value) {
        root = addRecursive(root, value);
    }

    // Recursive helper method for adding nodes to the binary tree
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value); // Insert a new node if the spot is empty
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    // In-order traversal using an explicit stack (non-recursive)
    
    public void print() {
        Stack<Node> stk = new Stack<Node>();
        Node cur = this.root;

        while (cur != null) {
            stk.push(cur);   
            cur = cur.left;  
        }
    
        while (cur != null) {   
            cur = stk.pop();       
            System.out.println(cur.value); 

            if (cur.right != null) {
                cur = cur.right; 

                while (cur != null) {
                    stk.push(cur);   
                    cur = cur.left;  
                }
            } 
            else {
                cur = null;   
            }
     }
    }
    
     

    // Main method to run the program
    public static void main(String[] args) {
        BinaryTreee tree = new BinaryTreee();

        // Adding nodes to the binary tree
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);

        // Print the binary tree using in-order traversal
        System.out.println("In-order traversal of the tree:");
        tree.print(); // Output should be: 2 3 4 5 6 7 8
    }
}
