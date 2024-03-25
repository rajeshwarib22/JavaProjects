package bhirud.VIRUS;

import bhirud.INTERFACE.Viral;

/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Exam1 : Fundamental JAVA OOP
 */



public abstract class ComputerVirus implements Viral{
    //variables for class ComputerVirus
    private String virusID;
    private String virusName;
    private int virusSize;
    private String virusPayload;
    private String virusTrigger;
    private String virusTarget;
    private boolean isEncrypted;
    public static int virusCount= 0;
 
    
    // Constructors for class ComputerVirus
    public ComputerVirus() {
        virusCount++;
    }

    public ComputerVirus(String virusID, String virusName, int virusSize, String virusPayload, String virusTrigger, String virusTarget, boolean isEncrypted) {
        this.virusID = virusID;
        this.virusName = virusName;
        this.virusSize = virusSize;
        this.virusPayload = virusPayload;
        this.virusTrigger = virusTrigger;
        this.virusTarget = virusTarget;
        this.isEncrypted = isEncrypted;
        virusCount++;
    }
    
    // Getter and Setters fro class ComputerVirus
    public String getVirusID() {
        return virusID;
    }

    public void setVirusID(String virusID) {
        this.virusID = virusID;
    }

    public String getVirusName() {
        return virusName;
    }

    public void setVirusName(String virusName) {
        this.virusName = virusName;
    }

    public int getVirusSize() {
        return virusSize;
    }

    public void setVirusSize(int virusSize) {
        this.virusSize = virusSize;
    }

    public String getVirusPayload() {
        return virusPayload;
    }

    public void setVirusPayload(String virusPayload) {
        this.virusPayload = virusPayload;
    }

    public String getVirusTrigger() {
        return virusTrigger;
    }

    public void setVirusTrigger(String virusTrigger) {
        this.virusTrigger = virusTrigger;
    }

    public String getVirusTarget() {
        return virusTarget;
    }

    public void setVirusTarget(String virusTarget) {
        this.virusTarget = virusTarget;
    }

    public boolean isIsEncrypted() {
        return isEncrypted;
    }

    public void setIsEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    public static int getVirusCount() {
        return virusCount;
    }

    public static void setVirusCount(int virusCount) {
        ComputerVirus.virusCount = virusCount;
    }
    
    
    // methos implementation
    
    // try to write this when you use toString() function
//    @Override
//    public String toString() {
//        return "Virus ID: " + virusID + 
//               "\nVirus Name: " + virusName +
//                "\nVirus Size: " + virusSize +
//                "\nVirus Payload: " + virusPayload +
//                "\nVirus Trigger: " + virusTrigger +
//                "\nVirus Target: " + virusTarget +
//                "\nEncrypted: " + (isIsEncrypted()?"YES":"NO");
//    }   
    @Override
    public String toString() {
        return "Virus ID: " + this.virusID + 
               "\nVirus Name: " + this.virusName +
                "\nVirus Size: " + this.virusSize +
                "\nVirus Payload: " + this.virusPayload +
                "\nVirus Trigger: " + this.virusTrigger +
                "\nVirus Target: " + this.virusTarget +
                "\nEncrypted: " + (isIsEncrypted()?"YES":"NO");
    }
}
