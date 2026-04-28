public class Linked {

    static class LinkedLinst {
        Cell first;

        private class Cell {
            int head;
            Cell tail;

            Cell(int val, Cell tl) {
                head = val;
                tail = tl;
            }
        }

        // Constructor to initialize the list
        LinkedLinst() {
            first = null;
        }

        // Add the item as the first cell in the list
        void add(int item) {
            Cell newFirst = new Cell(item, first);
            first = newFirst;
        }

        // Return the length of the list
        int length() {
            int count = 0;
            Cell i = first;
            while (i != null) {
                count++;
                i = i.tail;
            }
            return count;
        }

        // Return true or false depending on if the item can be found in the list
        boolean find(int item) {
            Cell j = first;
            while (j != null) {
                if (j.head == item) {
                    return true;
                }
                j = j.tail;
            }
            return false;
        }

        // Remove the item if it exists in the list
        void remove(int item) {
            if (first == null) return;

            if (first.head == item) {
                first = first.tail;
                return;
            }

            Cell k = this.first;
            while (k.tail != null) {
                if (k.tail.head == item) {
                    k.tail = k.tail.tail;
                    return;
                }
                k = k.tail;
            }
        }

        // Append another linked list to this one
        public void append(LinkedLinst b) {
            if (this.first == null) {
                this.first = b.first;
                b.first = null;
                return;
            }

            Cell nxt = this.first;
            while (nxt.tail != null) {
                nxt = nxt.tail;
            }
            nxt.tail = b.first;
        }
    }

    public static void main(String[] arg) {
        int[] aSizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000, 3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100, 4200, 4300, 4400, 4500, 4600, 4700, 4800, 4900, 5000, 5100, 5200, 5300, 5400, 5500, 5600, 5700, 5800, 5900, 6000, 6100, 6200, 6300, 6400, 6500, 6600, 6700, 6800, 6900, 7000, 7100, 7200, 7300, 7400, 7500, 7600, 7700, 7800, 7900, 8000, 8100, 8200, 8300, 8400, 8500, 8600, 8700, 8800, 8900, 9000, 9100, 9200, 9300, 9400, 9500, 9600, 9700, 9800, 9900, 10000
        };
        int bSize = 500;
        for (int aSize : aSizes) {
            LinkedLinst a = createLinkedList(aSize);
            LinkedLinst b = createLinkedList(bSize);

            long startTime = System.nanoTime();
            b.append(a);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println( aSize +" " +duration );
        }
    }

    public static LinkedLinst createLinkedList(int size) {
        LinkedLinst list = new LinkedLinst();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }
}
