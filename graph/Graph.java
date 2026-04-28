import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;





public class Graph {


    public static class Connections{
        City ntx;
        int time;

        Connections(City ntx, int time){
            this.ntx = ntx;
            this.time = time;
        }
    }
    public static class City{
        String name;
        ArrayList<Connections> connections;

        City(String name){
            this.name = name;
            this.connections = new ArrayList<>();
        }

        public void connect(City nxt, int dst){
         connections.add(new Connections(nxt, dst));
        }
    }
    private static Integer hash(String name, int mod) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
        hash = (hash*31 + name.charAt(i)) % mod;
        }
        return hash;
        }

        
        public static class Map {
            private City[] cities;
            private final int mod = 541;
            int collisions = 0;
        

            public Map(String file){
            cities = new City[mod];
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            String city1 = row[0];
            String city2 = row[1];
            int dist = Integer.parseInt(row[2]);

            City one = lookup(city1);
            City two = lookup(city2);

            one.connect(two, dist);
            two.connect(one, dist);
            }
            } catch (Exception e) {
            System.out.println(" file " + file + " not found or corrupt");
              }
            }
            
        
            private City lookup(String key) {
            int index = hash(key, mod);
            while(cities[index] != null){
              if (cities[index].name.equals(key)){
                return cities[index];
              }
              index = (index + 1) % mod;
              collisions++;
            }
            cities[index] = new City(key);
            return cities[index];
            }
        }

        public static class Paths {
    
            City[] path;
            int sp; 
        
            public Paths() {
        
                path = new City[100];
                sp = 0;
        
            }
        
            private Integer shortest(City from, City to) {
                if(from == to) {
                    return 0;
                }
                Integer shrt = null;
        
                for(int i = 0; i < sp; i++)
                {
                    if( path[i] == from ) {
                        return null;
                    }
                }
                path[sp++] = from;
                for(int j = 0; j < from.connections.size(); j++)
                {
                    Connections conn = from.connections.get(j);
                    Integer dist = shortest(conn.ntx, to);
        
                    if(dist != null) {
                        int totalDist = dist + conn.time;
                        if( (shrt == null) || ( totalDist < shrt)) { 
                                shrt = totalDist;
                        }
                    }
                }
                path[sp--] = null;
                return shrt;
            }
            
        
            public static void main(String[] args ) {
               
                Map A = new Map("trains.csv");
                Paths B = new Paths();
                City one = A.lookup("Göteborg");
                City two = A.lookup("Umeå");
                System.out.println("From: " + one.name + "  To: " + two.name);
                
                
                long t0 = System.nanoTime();
                Integer dist = B.shortest( one, two);
                long time = (System.nanoTime() - t0) / 1_000_000;
                System.out.println("shortest: " + dist + " min (" + time + " ms)");
        
            }
        }
}
        /*public static class Naive {
            
            public static void main(String[] args) {
                Graph.Map map = new Graph.Map("trains.csv"); 
                System.out.println("collision" + map.collisions);
                String from = "Sundsvall";
                String to = "Umeå";
                Integer max = 1000;
    
                long t0 = System.nanoTime();
                Integer dist = shortest(map.lookup(from), map.lookup(to), max);
                long time = (System.nanoTime() - t0)/1_000_000;
                System.out.println("shortest: " + dist + " min (" + time + " ms)");
                }
    
    
            private static Integer shortest(City from, City to, Integer max) {
                if (max < 0)
                return null;
                if(from == to)
                return 0;
               Integer shrt = null;
               for(int i = 0; i < from.connections.size(); i++){
                Connections conn = from.connections.get(i);
                Integer dist = shortest(conn.ntx , to, max -conn.time);

                if( dist != null){
                 int totalDis = dist + conn.time;
                 if(shrt == null || totalDis < shrt){
                    shrt = totalDis;
                 }
                }
               }
               return shrt;
            }
        }
    }*/


