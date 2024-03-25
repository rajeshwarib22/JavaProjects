package bhirud.VIRUS;

/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Exam1 : Fundamental JAVA OOP
 */



public class B_Virus extends ComputerVirus{
 private String BCode= "VIRUS_BETA";
    
    
    public B_Virus(String virusID, String virusPayload, String virusTrigger, String virusTarget, boolean isEncrypted) {   
        super(virusID, "B" + virusID, 6, virusPayload, virusTrigger, virusTarget, isEncrypted);
    }

    public String getBCode() {
        return BCode;
    }

    public void setBCode(String BCode) {
        this.BCode = BCode;
    }
       
    @Override
    public void displayMe() {
        System.out.println("** B Virus **");
        System.out.println("BCode: " + this.BCode);
        System.out.println(super.toString());
             
    }
}
