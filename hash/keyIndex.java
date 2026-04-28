
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class keyIndex {
static Area[] postnr;
int max = 100000;

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

public keyIndex(String file) {
    this.postnr = new Area[max];
try (BufferedReader br = new BufferedReader(new FileReader(file))) {
String line;
int i = 0;
while ((line = br.readLine()) != null) {
String[] row = line.split(",");
Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
postnr[code] = new Area(code, row[1], Integer.valueOf(row[2]));
}
} catch (Exception e) {
System.out.println(" file " + file + " not found");
}
}

public void collisions(int mod) {
    int mx = 20;
    int[] data = new int[mod];
    int[] cols = new int[mx];

    // keys[] are the zip codes
    for (int i = 0; i < max; i++) {
        if (postnr[i] != null) {  
        Integer index = postnr[i].getZipcode() % mod;
        if( data[index] == null){
         data[index]++;
        }
        for (int j = 0; j < mx; j++){
          cols[index] = postnr[i];
        }

    }
    }
    
}

public static void main(String[] args) {

   keyIndex ZipData = new keyIndex("postnummer.csv");
  
   
 
       ZipData.collisions(17389);  // Run the collision experiment for each modulus
     
        
    }

}
