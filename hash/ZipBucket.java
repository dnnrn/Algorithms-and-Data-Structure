import java.io.BufferedReader;
import java.io.FileReader;

public class ZipBucket {
    Bucket[] data;
    int max;
    int collisionCount;

    public class Area {
        Integer zipCode;
        String areaName;
        int population;

        private Area(Integer zipCode, String areaName, int population) {
            this.zipCode = zipCode;
            this.areaName = areaName;
            this.population = population;
        }

        public Integer getZipcode() {
            return zipCode;
        }
    }

    public class Bucket {
        Area[] area;

        public Bucket(Area code) {
            area = new Area[1];
            area[0] = code;
        }

        public void addBucket(Area code) {
            Area[] expand = new Area[area.length + 1];
            for (int i = 0; i < area.length; i++) {
                expand[i] = area[i];
            }
            expand[area.length] = code;
            area = expand;
        }
    }

    public ZipBucket(String file) {
        data = new Bucket[17389];
        collisionCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                Area postnr = new Area(code, row[1], Integer.valueOf(row[2]));
                
                long startTime = System.nanoTime();  // Start time for insertion
                collisionCheck(code, postnr);
                long endTime = System.nanoTime();  // End time for insertion
                
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println("file " + file + " not found");
        }
    }

    public static Integer hash(Integer code) {
        int m = 17389;
        code = (code % m);
        return code;
    }

    public void collisionCheck(Integer code, Area postnr) {
        Integer hashzip = hash(code);
        if (this.data[hashzip] != null) {
            this.data[hashzip].addBucket(postnr);
            collisionCount++;
        } else {
            this.data[hashzip] = new Bucket(postnr);
        }
    }

    public Area lookup(Integer key) {
        long startTime = System.nanoTime();  // Start time for lookup
        Integer hashzip = hash(key);  // Get the hashed index
    
        if (this.data[hashzip] == null) {
            System.out.println("Lookup time for zip code " + key + ": " + (System.nanoTime() - startTime) + " nanoseconds");
            return null;  // If no bucket exists at this hashed index, return null
        }
    
        // Check each area in the bucket to find a match
        for (int i = 0; i < this.data[hashzip].area.length; i++) {
            if (this.data[hashzip].area[i].zipCode.equals(key)) {  // Compare the original zipCode
                System.out.println("Lookup time for zip code " + key + ": " + (System.nanoTime() - startTime) + " nanoseconds");
                return this.data[hashzip].area[i];
            }
        }
    
        System.out.println("Lookup time for zip code " + key + ": " + (System.nanoTime() - startTime) + " nanoseconds");
        return null;  // Return null if no match is found
    }

    public static void main(String[] args) {
        ZipBucket ZipData = new ZipBucket("postnummer.csv");

        // Perform lookup for a sample zip code
        int[] testZipCodes = {11115, 50764, 98499};  // Replace with actual zip codes from your file
        for (int zipCode : testZipCodes) {
            long startTime = System.nanoTime();
            Area b = ZipData.lookup(zipCode);
            long endTime = System.nanoTime();
            long lookupDuration = (endTime - startTime);
            if (b != null) {
                System.out.println("Area Name: " + b.areaName + ", Population: " + b.population + " (Lookup took: " + lookupDuration + " nanoseconds)");
            } else {
                System.out.println("Zip code " + zipCode + " not found (Lookup took: " + lookupDuration + " nanoseconds)");
            }
        }


        // Print the number of collisions
        System.out.println("Number of collisions: " + ZipData.collisionCount);
    }
}
