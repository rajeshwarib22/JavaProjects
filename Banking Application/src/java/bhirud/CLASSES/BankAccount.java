package bhirud.CLASSES;

import bhirud.DBSPT.DBConnect;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


/**
 *
 * @author : Rajeshwari Bhirud 
 * Date : 04/06/2024
 * 
 * This is a Entity class Bank Account that will populate tables 
 * account_registry, account_savings and account_checking.
 * 
 * It implements other functionalities like show a specific account based on account number,
 * show all bank account, make deposit or withdrawal from both checking and savings account
 */


public class BankAccount {
    private String accountNumber;
    private String lastName;
    private String firstName;
    private String taxIDNumber;
    private double checkingBalance;
    private double minCheckingBalance;
    private double maxCheckingWithdrawal;
    private double savingsBalance;
    private double minSavingBalance;
    private double maxSavingWithdrawal;
    private double savingsInterestRate;
    public static int AccountCount = 0;

    public BankAccount(String accountNumber, String lastName, String firstName, String taxIDNumber, double checkingBalance,  double savingsBalance,  double savingsInterestRate) {
        this.accountNumber = accountNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.taxIDNumber = taxIDNumber;
        this.checkingBalance = checkingBalance;
        this.minCheckingBalance = checkingBalance * 0.25;
        this.maxCheckingWithdrawal = checkingBalance * 0.40;
        this.savingsBalance = savingsBalance;
        this.minSavingBalance = savingsBalance * 0.55;
        this.maxSavingWithdrawal = savingsBalance * 0.20;
        this.savingsInterestRate = savingsInterestRate;
        AccountCount++;
    }

    public BankAccount(String accountNumber, String lastName, String firstName, String taxIDNumber) {
        this.accountNumber = accountNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.taxIDNumber = taxIDNumber;
    }

    
    
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTaxIDNumber() {
        return taxIDNumber;
    }

    public void setTaxIDNumber(String taxIDNumber) {
        this.taxIDNumber = taxIDNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public double getMinCheckingBalance() {
        return minCheckingBalance;
    }

    public void setMinCheckingBalance(double minCheckingBalance) {
        this.minCheckingBalance = minCheckingBalance;
    }

    public double getMaxCheckingWithdrawal() {
        return maxCheckingWithdrawal;
    }

    public void setMaxCheckingWithdrawal(double maxCheckingWithdrawal) {
        this.maxCheckingWithdrawal = maxCheckingWithdrawal;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public double getMinSavingBalance() {
        return minSavingBalance;
    }

    public void setMinSavingBalance(double minSavingBalance) {
        this.minSavingBalance = minSavingBalance;
    }

    public double getMaxSavingWithdrawal() {
        return maxSavingWithdrawal;
    }

    public void setMaxSavingWithdrawal(double maxSavingWithdrawal) {
        this.maxSavingWithdrawal = maxSavingWithdrawal;
    }

    public double getSavingsInterestRate() {
        return savingsInterestRate;
    }

    public void setSavingsInterestRate(double savingsInterestRate) {
        this.savingsInterestRate = savingsInterestRate;
    }

    @Override
    public String toString() {
        return "Bank Account Details:" + 
                "\nAccount Number: " + this.accountNumber +
                "\nLast Name: " + this.lastName +
                "\nFirst Name: " + this.firstName +
                "\nTax ID Number: " + this.taxIDNumber +
                "\nCecking Balance: " + this.checkingBalance +
                "\nMinimum Checking Balance: " + this.minCheckingBalance +
                "\nMaximum Checking Withdrawal: " + this.maxCheckingWithdrawal +
                "\nSavings Balance:" + this.savingsBalance +
                "\nMinimum Saving Balance: " + this.minSavingBalance + 
                "\nMaximum Saving Withdrawal: " + this.maxSavingWithdrawal +
                "\nSavings Interest Rate: " + this.savingsInterestRate;
    }
    
    public void displayAccountInfo(){
        System.out.println(this.toString());
    }
    
    public boolean depositToChecking(double d){
        if(d >= 0){
            this.checkingBalance = this.checkingBalance + d;
            String checkingBalanceFormatted = String.format("$%,.2f", this.checkingBalance);
            System.out.println(checkingBalanceFormatted+" deposited to Checking");
            this.maxCheckingWithdrawal = this.checkingBalance * 0.40;
            this.minCheckingBalance = this.checkingBalance * 0.25;
        return true;
        }
        return false;
    }
    

    public String depositChecking(double amount) throws SQLException, ClassNotFoundException {
        String htmlFormat;
        String updateQuery = "UPDATE account_checking SET CheckingBalance = ? WHERE AccountNumber = ?;";

        if (depositToChecking(amount)) {

            try (Connection conn = DBConnect.connectToDB("BOL")) {
                DBConnect.UseDB(conn, "BOL");

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setDouble(1, this.checkingBalance);
                    pstmt.setString(2, this.accountNumber);
                    pstmt.executeUpdate();
                }

                String checkingBalanceFormatted = String.format("$%,.2f", this.checkingBalance);
                String amountFormatted = String.format("$%,.2f", amount);
                htmlFormat = "<h2>" + amountFormatted + " deposited to the Checking Account</h2>" +
                        "<h2>Current Checking Balance is " + checkingBalanceFormatted + "</h2><br>";

                return htmlFormat;
            }
        } else {
            String checkingBalanceFormatted = String.format("$%,.2f", this.checkingBalance);
            htmlFormat = "<h2>Deposit Cannot Be Made</h2>" +
                    "<h1>Current Checking Balance is " + checkingBalanceFormatted + "</h1><br>";

            return htmlFormat;
        }
    }

    public boolean depositToSavings(double d){
        if(d >=0){
            this.savingsBalance = this.savingsBalance + d;
            String savingsBalanceFormatted = String.format("$%,.2f", this.savingsBalance);
            System.out.println(savingsBalanceFormatted+" deposited to Savings");
            this.maxSavingWithdrawal = this.savingsBalance * 0.20;
            this.minSavingBalance = this.savingsBalance * 0.55;
            return true;
        }
        return false;
    }
    
    public String depositSavings(double amount) throws ClassNotFoundException, SQLException{
        String htmlFormat;
        String updateQuery = "UPDATE account_savings SET SavingsBalance = ? WHERE AccountNumber = ?;";

        if(depositToSavings(amount)){
            try (Connection conn = DBConnect.connectToDB("BOL")) {
                DBConnect.UseDB(conn, "BOL");

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setDouble(1, this.savingsBalance);
                    pstmt.setString(2, this.accountNumber);
                    pstmt.executeUpdate();
                }


                String savingsBalanceFormatted = String.format("$%,.2f", this.savingsBalance);
                String amountFormatted = String.format("$%,.2f", amount);
                htmlFormat = "<h2> " + amountFormatted + " deposited to the Savings Account</h2>" +
                "<h2>Current Savings Balance is " + savingsBalanceFormatted + "</h2><br>" ;

                return htmlFormat;
            }
        } else {
            String savingsBalanceFormatted = String.format("$%,.2f", this.savingsBalance);
            htmlFormat = "<h2> Deposit Cannot Be Made</h2>" +
            "<h1>Current Savings Balance is " + savingsBalanceFormatted + "</h1><br>" ;

            return htmlFormat;
        }
    }

    
    public String withdrawFromChecking(double d) throws ClassNotFoundException, SQLException {
    String htmlFormat;
    
    if (this.checkingBalance >= d && ((this.checkingBalance - d) > this.minCheckingBalance) && (d <= this.maxCheckingWithdrawal)&& (d >=0)) {
        this.checkingBalance -= d;
        String amountFormatted = String.format("$%,.2f", d);
        this.maxCheckingWithdrawal = this.checkingBalance * 0.40;
        this.minCheckingBalance = this.checkingBalance * 0.25;
        
        String updateQuery = "UPDATE account_checking SET CheckingBalance = ? WHERE AccountNumber = ?";
        
        try (Connection conn = DBConnect.connectToDB("BOL")) {
            DBConnect.UseDB(conn, "BOL");

            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setDouble(1, this.checkingBalance);
                pstmt.setString(2, this.accountNumber);
                pstmt.executeUpdate();
            }

            String checkingBalanceFormatted = String.format("$%,.2f", this.checkingBalance);
            System.out.println(checkingBalanceFormatted + " withdrawn from Checking");

            htmlFormat = "<h2>" + amountFormatted + " withdrawn from Checking</h2>" +
                "<h2>Current Checking Balance is " + checkingBalanceFormatted + "</h2><br>";

            return htmlFormat;
        }
    } else {
        System.out.println("Withdraw cannot be made");
        String checkingBalanceFormatted = String.format("$%,.2f", this.checkingBalance);
        htmlFormat = "<h2>Withdraw Cannot Be Made</h2>" +
            "<h1>Current Checking Balance is " + checkingBalanceFormatted + "</h1><br>";

        return htmlFormat;
    }
}

    
    public String withdrawFromSavings(double d) throws ClassNotFoundException, SQLException{
        String htmlFormat;
        
        if(this.savingsBalance >= d && ((this.savingsBalance - d) > this.minSavingBalance) && (d <= this.maxSavingWithdrawal) && (d >=0)){
            this.savingsBalance -= d;
            String savingsBalanceFormatted = String.format("$%,.2f", this.savingsBalance);
            String amountFormatted = String.format("$%,.2f", d);
            this.maxSavingWithdrawal = this.savingsBalance * 0.20;
            this.minSavingBalance = this.savingsBalance * 0.55;
            
            String updateQuery = "UPDATE account_savings SET SavingsBalance = ? WHERE AccountNumber = ?;";
            
            try (Connection conn = DBConnect.connectToDB("BOL")) {
                DBConnect.UseDB(conn, "BOL");

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setDouble(1, this.savingsBalance);
                    pstmt.setString(2, this.accountNumber);
                    pstmt.executeUpdate();
                }
            
            
            System.out.println(savingsBalanceFormatted+" withdrawn from Savings");
            htmlFormat = "<h2> " + amountFormatted + " withdrawn from Savings</h2>" +
            "<h2>Current Savings Balance is " + savingsBalanceFormatted + "</h2><br>" ;
            
            return htmlFormat;
            }
        }else{
            System.out.println("Withdraw cannot be made");
            String savingsBalanceFormatted = String.format("$%,.2f", this.savingsBalance);
            htmlFormat = "<h2> Withdraw Cannot Be Made</h2>" +
            "<h1>Current Savings Balance is " + savingsBalanceFormatted + "</h1><br>" ;
            
            return htmlFormat;
        }
    }
    
    public String toHTML() {
       String checkingBalanceFormatted = String.format("$%,.2f", this.checkingBalance);
       String minCheckingBalanceFormatted = String.format("$%,.2f", this.minCheckingBalance);
       String maxCheckingWithdrawalFormatted = String.format("$%,.2f", this.maxCheckingWithdrawal);
       String savingsBalanceFormatted = String.format("$%,.2f", this.savingsBalance);
       String minSavingBalanceFormatted = String.format("$%,.2f", this.minSavingBalance);
       String maxSavingWithdrawalFormatted = String.format("$%,.2f", this.maxSavingWithdrawal);
       String savingsInterestRateFormatted = String.format("%.2f%%", this.savingsInterestRate);

       String printHTML = "<h1>Bank Account</h1><br>" +
               "<h1>Last Name: " + this.lastName + "</h1>" +
               "<h1>First Name: " + this.firstName + "</h1><br>" +
               "<h1 style=\"color: blue;\">Account Number: " + this.accountNumber + "</h1>" +
               "<h1 style=\"color: blue;\">Tax ID Number: " + this.taxIDNumber + "</h1><br>" +
               "<h2>***CHECKING***</h2>" +
               "<h3>Checking Balance: " + checkingBalanceFormatted + 
               " Min Checking Balance: " + minCheckingBalanceFormatted + 
               " Max Checking Withdrawal: " + maxCheckingWithdrawalFormatted + "</h3>" +
               "<h2>***SAVINGS***</h2>" +
               "<h3>Savings Balance: " + savingsBalanceFormatted + 
               " Min Saving Balance: " + minSavingBalanceFormatted + 
               " Max Saving Withdrawal: " + maxSavingWithdrawalFormatted + 
               " Savings Interest Rate: " + savingsInterestRateFormatted + "</h3>";

       return printHTML;
   }


    
    public String[] toSQL(){
        String sqlStatement[] = new String[3];
        
        sqlStatement[0] = "insert into account_registry values('"+this.accountNumber+"','"+this.lastName+"','"+this.firstName+"','"+this.taxIDNumber+"');";
        sqlStatement[1] = "insert into account_checking(AccountNumber,CheckingBalance) values('"+this.accountNumber+"',"+this.checkingBalance+");";
        sqlStatement[2] = "insert into account_savings(AccountNumber,SavingsBalance,SavingsInterestRate) values('"+this.accountNumber+"',"+this.savingsBalance+","+this.savingsInterestRate+");";
        
        return sqlStatement;
    }
    
    public void writeToSQLFile() throws IOException{
        String dir = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Bhirud_Assignment3\\src\\java\\bhirud\\SQL_FILES\\";
        String fileLoc = dir + "AccountsPOP.sql";
        FileWriter fw  = new FileWriter(fileLoc,true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for (String sql : this.toSQL()) {
                bw.write(sql + "\n");
            }
        }
        
    }
    
    public void writeToDatabase() throws ClassNotFoundException, SQLException{
        System.out.println("Writing to database...");
        for (String sql : toSQL()) {
            DBConnect.executeUpdate(sql, "BOL");
        }
        System.out.println("Write complete.");
    
    }
    
    public String showAccount() {
        String checkingBalanceFormatted = String.format("$%,.2f", this.checkingBalance);
        String savingsBalanceFormatted = String.format("$%,.2f", this.savingsBalance);
        

        String accountInfo = "<h1>Bank Account</h1>" +
                "<h1>Last Name: " + this.lastName + "&nbsp;&nbsp;&nbsp;&nbsp;First Name: "+ this.firstName+"</h1>" +
                "<h1 style=\"color: blue;\">Account Number: " + this.accountNumber + "&nbsp;&nbsp;&nbsp;&nbsp;Tax ID Number: "+ this.taxIDNumber +"</h1>" +
                "<h2 style=\"color: green;\">[CHECKING]</h2>" +
                "<h3>Checking Balance: " + checkingBalanceFormatted + 
                "<h2 style=\"color: green;\">[SAVINGS]</h2>" +
                "<h3>Savings Balance: " + savingsBalanceFormatted;

        return accountInfo;
    }
    
    // method used to give html format for DynamicQuery servlet
    public String toHTML_Summary(){
        String htmlFormat = "<div title=\"showDyanamicQuery\">"
                            + "<h2>Bank Account</h2>" +
                            "<h2 style=\"margin-bottom: 0;\">Last Name: " + this.lastName +"</h2>"+
                            "<h2 style=\"margin-top: 0;\">First Name: " + this.firstName +"</h2>"+
                            "<h2 style=\"margin-bottom: 0; color: blue;\">Account Number " + this.accountNumber +"</h2>"+
                            "<h2 style=\"margin-top: 0; color: blue;\">Tax ID Number: " + this.taxIDNumber +"</h2><br>"
                            +"</div>";
        
        
        return htmlFormat;
    }
    
}
