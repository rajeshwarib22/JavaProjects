package bhirud.VIRUS;

/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Exam1 : Fundamental JAVA OOP
 */


public class C_Virus extends ComputerVirus{
    private String CCode= "VIRUS_GAMMA";
    
    public C_Virus(String virusID, String virusPayload, String virusTrigger, String virusTarget, boolean isEncrypted) {   
        super(virusID, "C" + virusID, 8, virusPayload, virusTrigger, virusTarget, isEncrypted);
    }

    public String getCCode() {
        return CCode;
    }

    public void setCCode(String CCode) {
        this.CCode = CCode;
    }
    
    @Override
    public void displayMe() {
        System.out.println("** C Virus **");
        System.out.println("CCode: " + this.CCode);
        System.out.println(super.toString());
             
    }
    
}
