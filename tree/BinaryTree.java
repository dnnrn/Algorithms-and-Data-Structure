
import java.util.Random;
import java.util.Stack;

public class BinaryTree {
private class Node {
    private Integer value;
    private Node left, right;

private Node(Integer value) {
this.value = value;
this.left = this.right = null;
}
}

private Node root;
public BinaryTree() {
root = null;
}

public void print() {
Stack<Node> stk = new Stack<Node>();
Node cur = this.root;
// move to the leftmost node
while(cur != null) {
// print value of node
if(cur.right != null) {
// move to the leftmost node, push nodes as you go
} else {
// pop a node from the stack
}
}
// done
}

public void add(Integer value){

    if(root.value == value)
    return;
    if(root == null){
     root = new Node(value);
        return;
    }
    Node i = root;
       
    while(true){
       if(value < i.value && value != i.value){
           if(i.left == null){
            i.left = new Node(value);
            break;
        }
          i = i.left;
    }
    else if(value > i.value && value != i.value){
        if(i.right == null){
            i.right = new Node(value);
            break;
        }
           i = i.right;
    } 
        break;
    }
}

boolean lookup(Integer key){
   Node i = root;
   while(i != null){
   if( i == null){
    return false;
   }
   if(key.equals(i.value)){
    return true;
   }
   else if(key < i.value){
    i = i.left;
   }
   else{
    i = i.right;
   }
}
 return false; 
}
public static void main(String[] args) {
    int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000, 3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100, 4200, 4300, 4400, 4500, 4600, 4700, 4800, 4900, 5000
    };
    for (int size : sizes) {
        BinaryTree tree = new BinaryTree();
        Random random = new Random();
        
        
        for(int i = 0; i < size; i++){
            tree.add(random.nextInt(50000));
        }

        long startTime = System.nanoTime();
        tree.add(random.nextInt(50000));
        long endTime = System.nanoTime();

        long staartTime = System.nanoTime();
        tree.lookup(random.nextInt(50000));
       long  endTiime = System.nanoTime();
        long addDuration = (endTime - startTime);
        long lookupDuration = (endTiime - staartTime);
        System.out.println( size + "        " + addDuration +"           "+ lookupDuration);
    }
}
}

