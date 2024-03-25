package bhirud.MAIN;

import bhirud.FACTORY.VirusBuilder;
import bhirud.VIRUS.ComputerVirus;


/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Exam1 : Fundamental JAVA OOP
 */


public class Bhirud {
    public static void main(String[] args){
        ComputerVirus[] viruses = VirusBuilder.buildViruses(1,1,1);
        VirusBuilder.displayViruses(viruses);
    }
}
