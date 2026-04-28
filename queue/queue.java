package queue;

class Queue {
    Node head;
    Node tail;
    
    private class Node {
    Integer item;
    Node next;
    Node prev;
   
    private Node(Integer item) {
        this.item = item;
        this.next = null;
        this.prev = null;

    }
    }
    
    public Queue() {
       this.head = null;
       this.tail = null;
    }
    
   
    public void enqueue(Integer item) {
      if (head == null){
        Node newNode = new Node(item);
        head = newNode;
        tail = newNode;
      } else{
        Node newNode = new Node(item);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
      }
    }
        /*if(head == null){
        head = new Node(item, null);
        }
        else{
        Node i = head;
        while (i.next != null){
        i = i.next;
     }
       i.next = new Node(item, null);*/
    

    public Integer dequeue() {
    if(head == null)
    return null;
    else{
    Integer i = head.item;
     head = head.next;
     if(head != null)
     head.prev = null;
     else{
        tail = null;
     }
     return i;
    }
    }
    
    public static void main(String[] args) {
        int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000, 3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100, 4200, 4300, 4400, 4500, 4600, 4700, 4800, 4900, 5000, 5100, 5200, 5300, 5400, 5500, 5600, 5700, 5800, 5900, 6000, 6100, 6200, 6300, 6400, 6500, 6600, 6700, 6800, 6900, 7000, 7100, 7200, 7300, 7400, 7500, 7600, 7700, 7800, 7900, 8000, 8100, 8200, 8300, 8400, 8500, 8600, 8700, 8800, 8900, 9000, 9100, 9200, 9300, 9400, 9500, 9600, 9700, 9800, 9900, 10000, 10100, 10200, 10300, 10400, 10500, 10600, 10700, 10800, 10900, 11000, 11100, 11200, 11300, 11400, 11500, 11600, 11700, 11800, 11900, 12000, 12100, 12200, 12300, 12400, 12500, 12600, 12700, 12800, 12900, 13000, 13100, 13200, 13300, 13400, 13500, 13600, 13700, 13800, 13900, 14000, 14100, 14200, 14300, 14400, 14500, 14600, 14700, 14800, 14900, 15000, 15100, 15200, 15300, 15400, 15500, 15600, 15700, 15800, 15900, 16000, 16100, 16200, 16300, 16400, 16500, 16600, 16700, 16800, 16900, 17000, 17100, 17200, 17300, 17400, 17500, 17600, 17700, 17800, 17900, 18000, 18100, 18200, 18300, 18400, 18500, 18600, 18700, 18800, 18900, 19000, 19100, 19200, 19300, 19400, 19500, 19600, 19700, 19800, 19900, 20000
        };
        for (int size : sizes) {
            Queue queue = createLinkedList(size);
    
            long startTime = System.nanoTime();
            queue.enqueue(size);
            long endTime = System.nanoTime();

            long staartTime = System.nanoTime();
            queue.dequeue();
           long  endTiime = System.nanoTime();
            long enqueueDuration = (endTime - startTime);
            long dequeueDuration = (endTiime - staartTime);
            System.out.println( size + "        " + enqueueDuration +"           "+ dequeueDuration);
        }
    }

    public static Queue createLinkedList(int size) {
        Queue list = new Queue();
        for (int i = 0; i < size; i++) {
            list.enqueue(i);
        }
        return list;
    }
    }