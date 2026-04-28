import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class ZipInt {
Area[] postnr;
int max = 10000;

public class Area {
Integer zipCode;
String areaName;
int population;

private Area (Integer zipCode, String areaName,int population){
    this.zipCode = zipCode;
    this.areaName = areaName;
    this.population = population;
  }
  public Integer getZipcode(){
    return zipCode;
  }
}

public ZipInt(String file) {
    this.postnr = new Area[this.max];
try (BufferedReader br = new BufferedReader(new FileReader(file))) {
String line;
int i = 0;
while ((line = br.readLine()) != null && i < this.max) {
String[] row = line.split(",");
Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
postnr[i++] = new Area(code, row[1], Integer.valueOf(row[2]));
}
this.max = i;
} catch (Exception e) {
System.out.println(" file " + file + " not found");
}
}

public boolean linearLookup(Integer key){
    for(int i = 0; i < this.max; i++){
        if(postnr[i].getZipcode().equals(key)) {
        return true;
    }
}
        return false; 
}

public boolean binaryLookup(Integer zip, int low, int high){
    if(low > high){
        return false;
    }
    int mid = low + (high - low)/2;
    Integer midZip = postnr[mid].getZipcode();

    if(midZip.equals(zip)){
        return true;
        }
    if(midZip.compareTo(zip) > 0){
        return binaryLookup(zip, low, mid - 1);
    }
    else {
        return binaryLookup(zip, mid + 1, high);
    }
}

public static void main(String[] args) {

   ZipInt ZipData = new ZipInt("postnummer.csv");

        Integer key1 = 11115;
        long startTime = System.nanoTime();
        boolean found = ZipData.binaryLookup(key1, 0 ,ZipData.max -1);
        long endTime = System.nanoTime();
        
        Integer key2 = 98499;
        long staartTime = System.nanoTime();
        boolean found2 = ZipData.binaryLookup(key2, 0 ,ZipData.max -1);
        long endTiime = System.nanoTime();

        Integer key3 = 11115;
        long startTime3 = System.nanoTime();
        boolean found3 = ZipData.linearLookup(key3);
        long endTime3 = System.nanoTime();
        
        Integer key4 = 98499;
        long staartTime4 = System.nanoTime();
        boolean found4 = ZipData.linearLookup(key4);
        long endTiime4 = System.nanoTime();

        long lookupDuration1 = (endTime - startTime);
        long lookupDuration2 = (endTiime - staartTime);
        long lookupDuration3 = (endTime3 - startTime3);
        long lookupDuration4 = (endTiime4 - staartTime4);
        System.out.println("Lookup duration for zip code " + key1 + " " + lookupDuration1 + " nanoseconds");
        System.out.println("Lookup duration for zip code " + key2 + ": " + lookupDuration2 + " nanoseconds");
        System.out.println("LinearLookup duration for zip code " + key3 + ": " + lookupDuration3 + " nanoseconds");
        System.out.println("LinearLookup duration for zip code " + key4 + ": " + lookupDuration4 + " nanoseconds");
    }

}
