package bhirud.VIRUS;

/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Exam1 : Fundamental JAVA OOP
 */


public class A_Virus  extends ComputerVirus{
    private String ACode= "VIRUS_ALPHA";
    
    
    public A_Virus(String virusID, String virusPayload, String virusTrigger, String virusTarget, boolean isEncrypted) {   
        super(virusID, "A" + virusID, 4, virusPayload, virusTrigger, virusTarget, isEncrypted);
    }

    public String getACode() {
        return ACode;
    }

    public void setACode(String ACode) {
        this.ACode = ACode;
    }
       
    @Override
    public void displayMe() {
        System.out.println("** A Virus **");
        System.out.println("ACode: " + this.ACode);
        //System.out.println(toString());   // rewrite as super.toString();
        System.out.println(super.toString());
    }
    
}
