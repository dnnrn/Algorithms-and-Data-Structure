import java.io.BufferedReader;
import java.io.FileReader;

public class ZipLinearProbing {
    Area[] data;  // Single array for storing Area entries directly
    int max;
    int collisionCount;

    // Inner class to represent an Area (zip code entry)
    public class Area {
        Integer zipCode;
        String areaName;
        int population;

        private Area(Integer zipCode, String areaName, int population) {
            this.zipCode = zipCode;
            this.areaName = areaName;
            this.population = population;
        }
    }

    // Constructor to initialize the array and read data from the file
    public ZipLinearProbing(String file, int arraySize) {
        data = new Area[arraySize];  // Initialize the array with the provided size
        collisionCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                Area postnr = new Area(code, row[1], Integer.valueOf(row[2]));
                long startTime = System.nanoTime();
                addEntry(code, postnr);
                long endTime = System.nanoTime();
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    // Hash function
    public static Integer hash(Integer code, int arraySize) {
        return code % arraySize;
    }

    // Add an entry to the array using linear probing
    public void addEntry(Integer code, Area postnr) {
        int index = hash(code, data.length);
        while (data[index] != null) {  // Linear probing: move forward if slot is occupied
            collisionCount++;  // Increment collision count
            index = (index + 1) % data.length;  // Wrap around if necessary
        }
        data[index] = postnr;  // Insert the entry
    }

    // Lookup a zip code using linear probing
    public Area lookup(Integer key) {
        int index = hash(key, data.length);
        while (data[index] != null) {  // Keep searching until an empty slot is found
            if (data[index].zipCode.equals(key)) {
                return data[index];  // Return the entry if found
            }
            index = (index + 1) % data.length;  // Move forward and wrap around if needed
        }
        return null;  // Return null if not found
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        // Create a ZipLinearProbing instance with an array size of 20000 (adjust as needed)
        ZipLinearProbing ZipData = new ZipLinearProbing("postnummer.csv", 20000);

        // Test lookup for multiple entries
        int[] testZipCodes = { 11115, 50764, 98499};  // Replace with actual zip codes from your file
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

        // Print the number of collisions encountered
        System.out.println("Number of collisions: " + ZipData.collisionCount);
    }
}
