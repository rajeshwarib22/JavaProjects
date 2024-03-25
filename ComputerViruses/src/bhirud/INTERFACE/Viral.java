package bhirud.INTERFACE;

import java.util.Random;

/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Exam1 : Fundamental JAVA OOP
 */


public interface Viral {
    
    public static String[ ] ltrs= {"A","B","C","D","E","F"};
    public static String[ ] digits= {"1","2","3","4","5","6"};
    public static String[ ] payload= {"Worm", "Trojan Horse", "Spyware"};
    public static String[ ] trigger= {"File Download", "Macro Run"};
    public static String[ ] target= {"Windows", "Linux"};
    public abstract void displayMe();
    
    public static String generateID(){
        Random random = new Random();
        String alplhaSeq = ltrs[random.nextInt(ltrs.length)];
        alplhaSeq = alplhaSeq + digits[random.nextInt(digits.length)];
        alplhaSeq = alplhaSeq + ltrs[random.nextInt(ltrs.length)];
       
        return alplhaSeq;
    }
}
