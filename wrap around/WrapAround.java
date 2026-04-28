public class WrapAround {

    private Integer[] queue;
    private int k;
    private int i;
    private int n;

     public WrapAround(int size){
        queue = new Integer[size];
        k = 0;
        i = 0;
        n = size;
     }

    public void enqueue(Integer item) {
        if(((k + 1)% n) == i ){
            Integer[] newArray = new Integer[n * 2];
            for( int j = 0; j < n ; j++){
                 newArray[j] = queue[(i + j) % n];
            }
            queue = newArray;
            i = 0;
            k = n;
            n = newArray.length;
        }
       queue[k] = item;
       k = (k + 1)% n;
    }

      public Integer dequeue() {
      if (i == k)
      return null;
       
        Integer element = queue[i];
        queue[i] = null;
        i = (i + 1) % n;
        return element;
      }

      
      public static void main(String[] args) {
          int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000, 3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100, 4200, 4300, 4400, 4500, 4600, 4700, 4800, 4900, 5000

          };
          for (int size : sizes) {
            WrapAround queue = new WrapAround(size);

           
            long startTime = System.nanoTime();
            for (int i = 1; i <= size; i++){
                queue.enqueue(i);  
            }
            long endTime = System.nanoTime();
            long enqueueDuration = (endTime - startTime);
            
           
            long startDequeueTime = System.nanoTime();
            for (int i = 1; i <= size; i++)
            {
             Integer dequeuedItem = queue.dequeue(); 
             if(dequeuedItem == null)
             break;
            }
            long endDequeueTime = System.nanoTime();
            long dequeueDuration = (endDequeueTime - startDequeueTime);

        
            System.out.println( size + "            " + enqueueDuration + "            " + dequeueDuration );
        }
    }

}
