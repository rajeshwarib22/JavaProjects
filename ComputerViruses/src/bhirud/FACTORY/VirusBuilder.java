package bhirud.FACTORY;

import bhirud.INTERFACE.Viral;
import bhirud.VIRUS.A_Virus;
import bhirud.VIRUS.B_Virus;
import bhirud.VIRUS.C_Virus;
import bhirud.VIRUS.ComputerVirus;
import java.util.Random;

/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Exam1 : Fundamental JAVA OOP
 */


public class VirusBuilder implements Viral{
    
    //method to build 1-d array for ComputerVirues of type A,B,C
    public static ComputerVirus[] buildViruses(int A,int B,int C){
        Random random = new Random();
        ComputerVirus[] viruses = new ComputerVirus[A + B + C];     //to generate array of ComputerViruses class where A+B+C is the n of virues

        for (int i = 0; i < A; i++) {
            String payload = Viral.payload[random.nextInt(Viral.payload.length)];
            String trigger = Viral.trigger[random.nextInt(Viral.trigger.length)];
            String target = Viral.target[random.nextInt(Viral.target.length)];
            viruses[i] = new A_Virus(Viral.generateID(), payload,trigger ,target, random.nextBoolean());
        }

        for (int i = A; i < A + B; i++) {
            String payload = Viral.payload[random.nextInt(Viral.payload.length)];
            String trigger = Viral.trigger[random.nextInt(Viral.trigger.length)];
            String target = Viral.target[random.nextInt(Viral.target.length)];
            viruses[i] = new B_Virus(Viral.generateID(), payload,trigger ,target, random.nextBoolean());
            
            // viruses[A+i] = new B_Virus(Viral.generateID(), payload,trigger ,target, random.nextBoolean());
        }

        for (int i = A + B; i < A + B + C; i++) {
            String payload = Viral.payload[random.nextInt(Viral.payload.length)];
            String trigger = Viral.trigger[random.nextInt(Viral.trigger.length)];
            String target = Viral.target[random.nextInt(Viral.target.length)];
            viruses[i] = new C_Virus(Viral.generateID(), payload,trigger ,target, random.nextBoolean());
            
            //viruses[A+B+i] = new C_Virus(Viral.generateID(), payload,trigger ,target, random.nextBoolean());
        }

        return viruses;
    
    }
    
    //method to display the 1-d array of build ComputerViruses
    public static void displayViruses(ComputerVirus[] cv){
        
        for (ComputerVirus virus : cv) {
            virus.displayMe();
            System.out.println("");
        }
    
    }

    @Override
    public void displayMe() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
