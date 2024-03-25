package inclass.cex1;

import java.text.DecimalFormat;

/**
 *
 * @author : Rajeshwari Bhirud 
 * Class exercise 1 to display data in table format
 */
public class ImperialParticles {
    
    public static void main(String[] args){
      int[] x_Coord = {8,8,20,16,7,20,12,20,18,3,3,7,18,5,18,9,12,2,19,15};
      int[] y_Coord = {17,14,9,6,14,3,5,11,15,7,10,8,3,9,16,18,8,1,18,12};
      String partcile = "";
      String color = "";
      String spin = "";
      double sum = 0;
      System.out.println("\t\t\t\t\t\tDistance\n");
      System.out.println("Particle\t X Coord \t Y Coord \t To Origin \t Color \t\t Spin \t\t Mass \t\t Particle Type");
      System.out.println("----------------------------------------------------------------------------------------------------------------------------");
      int i=0;
      double min = Integer.MAX_VALUE;
      double max=0;
      for (char a = 'A'; a <= 'T';++a){
        
        double distance = Math.sqrt(Math.pow(x_Coord[i],2)+Math.pow(y_Coord[i], 2));
        
        double mass = 0;
        
        if(x_Coord[i]%2!=0 && y_Coord[i]%2 == 0){
          color = "BLUE";
          spin = "LEFT";
          partcile = "Mu";
          mass = distance * 1.5;
        }else if(x_Coord[i]%2!=0 && y_Coord[i]%2 != 0){
          color = "RED";
          spin = "RIGHT";
          partcile = "Sigma";
          mass = distance * 1.75;
        }if(x_Coord[i]%2==0 && y_Coord[i]%2 == 0){
          color = "GREEN";
          spin = "UP";
          partcile = "Tau";
          mass = distance * 2.5;
        }if(x_Coord[i]%2==0 && y_Coord[i]%2 != 0){
          color = "BLACK";
          spin = "DOWN";
          partcile = "Omega";
          mass = distance * 2.75;
        }
        
        System.out.println("PA_"+a+"\t\t" +x_Coord[i]+"\t\t "+y_Coord[i]+"\t\t "+new DecimalFormat("##.##").format(distance)+"\t\t "+color+"\t\t "+spin+"\t\t "+new DecimalFormat("##.##").format(mass)+"\t\t "+partcile);
        i++;
        sum = sum + mass;
        min = Math.min(min, mass);
        max = Math.max(max, mass);
      }
      System.out.println("\n\n");
      System.out.println("Cumulative Particle Mass:\t"+new DecimalFormat("##.##").format(sum)+" imperials");
      System.out.println("Smallest Particle Mass:\t\t"+ new DecimalFormat("##.##").format(min)+" imperials");
      System.out.println("Largest Particle Mass:\t\t"+ new DecimalFormat("##.##").format(max)+" imperials");
      System.out.println("Average Particle Mass:\t\t"+ new DecimalFormat("##.##").format(sum/i)+" imperials");
    }
}
