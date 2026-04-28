


import java.util.Random;

public class QueueArray {

    private Integer[] queue;
    private int k;

     public QueueArray(int size){
        queue = new Integer[size];
        k = 0;
     }

    public void enqueue(Integer item) {
        if(k == queue.length){
            Integer[] newArray = new Integer[(queue.length)*2];
            for( int i = 0; i < queue.length; i++){
                 newArray[i] = queue[i];
            }
            queue = newArray;
        }
       queue[k] = item;
       k++;
    }

      public Integer dequeue() {
      if (k == 0){
      return null;}
       
        int element = queue[0];
        for (int i = 0; i < k-1; i++){
          queue[i] = queue[i+1];
        }
        queue[k-1] = null;
        k--;
        return element;
      }

      
      public static void main(String[] args) {
          int[] sizes = {
          };
          for (int size : sizes) {
            QueueArray queue = new QueueArray(size);

           
            long startTime = System.nanoTime();
            for (int i = 1; i <= size; i++){
                queue.enqueue(size);  
            }
            long endTime = System.nanoTime();
            long enqueueDuration = (endTime - startTime);
            
           
            long startDequeueTime = System.nanoTime();
            for (int i = 1; i <= size; i++){
            queue.dequeue(); 
            }
            long endDequeueTime = System.nanoTime();
            long dequeueDuration = (endDequeueTime - startDequeueTime);

        
            System.out.println( size + "            " + enqueueDuration + "            " + dequeueDuration );
        }
    }

}
