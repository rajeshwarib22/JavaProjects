package bhirud.INTERFACES;

import bhirud.CLASSES.BankAccount;
import bhirud.DBSPT.DBConnect;
import java.util.ArrayList;
import java.sql.*;

/**
 *@author : Rajeshwari Bhirud 
 * Date : 04/06/2024
 * 
 * This class is use in the servlets to search a particular bank account number
 * or to show all bank accounts present in the database
 */
public interface AccountUtility {
    
    public static String dir = "Bhirud_Assignment2\\src\\java\\bhirud\\SQL_FILES\\";
    
    // method is used to find a particular bank account
    public static BankAccount buildAccountFromQuery(String acct) throws ClassNotFoundException, SQLException {
        String query = "SELECT ar.AccountNumber, ar.LastName, ar.FirstName, ar.TaxIDNumber, ac.CheckingBalance, accs.SavingsBalance, accs.SavingsInterestRate " +
                    "FROM account_registry ar " +
                    "JOIN account_checking ac ON ar.AccountNumber = ac.AccountNumber " +
                    "JOIN account_savings accs ON ar.AccountNumber = accs.AccountNumber " +
                    "WHERE ar.AccountNumber = ?";

        BankAccount ba = null;

        Connection conn = DBConnect.connectToDB("BOL");
        DBConnect.UseDB(conn, "BOL");
        PreparedStatement pstmt = conn.prepareStatement(query);


         pstmt.setString(1, acct);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {

            String acctNo = rs.getString("AccountNumber");
            String lastname = rs.getString("LastName");
            String firstname = rs.getString("FirstName");
            String taxid = rs.getString("TaxIDNumber");
            double checkBal = rs.getDouble("CheckingBalance");
            double saveBal = rs.getDouble("SavingsBalance");
            double saveInterestRate = rs.getDouble("SavingsInterestRate");

            ba = new BankAccount(acctNo, lastname, firstname, taxid, checkBal, saveBal, saveInterestRate);

        } else {

            System.out.println("No account found with account number: " + acct);
        }


        return ba;
    }


    // method is used to find all bank accounts in the database
    public static ArrayList<BankAccount> buildAllAccunts() throws ClassNotFoundException, SQLException{
        ArrayList<BankAccount> ba = new ArrayList<>();
        String query = """
                       select ar.AccountNumber,ar.LastName,ar.FirstName,ar.TaxIDNumber,
                       ac.CheckingBalance,accs.SavingsBalance, accs.SavingsInterestRate from account_registry ar join 
                       account_checking ac on ar.AccountNumber = ac.AccountNumber
                       join account_savings accs on ar.AccountNumber = accs.AccountNumber;""";
        ResultSet rs = DBConnect.executeResultsQuery(query, "BOL");
        
        String acctNo;
        String lastname;
        String firstname;
        String taxid;
        double checkBal;
        double saveBal;
        double saveInterestRate;
        BankAccount acc = null ;
        
        while(rs != null && rs.next()){
            acctNo = rs.getString("AccountNumber");
            lastname = rs.getString("LastName");
            firstname = rs.getString("FirstName");
            taxid = rs.getString("TaxIDNumber");
            checkBal = rs.getDouble("CheckingBalance");
            saveBal = rs.getDouble("SavingsBalance");
            saveInterestRate = rs.getDouble("SavingsInterestRate");
            acc = new BankAccount(acctNo, lastname, firstname, taxid, checkBal, saveBal, saveInterestRate);
            ba.add(acc);
            
        }
        
        
        return ba;
    }
    
    public static ArrayList<BankAccount> buildAllAccuntsDynamicQuery() throws ClassNotFoundException, SQLException{
        ArrayList<BankAccount> ba = new ArrayList<>();
        String query = "select * from account_registry";
        ResultSet rs = DBConnect.executeResultsQuery(query, "BOL");
        
        String acctNo;
        String lastname;
        String firstname;
        String taxid;
        BankAccount acc = null ;
        
        while(rs != null && rs.next()){
            acctNo = rs.getString("AccountNumber");
            lastname = rs.getString("LastName");
            firstname = rs.getString("FirstName");
            taxid = rs.getString("TaxIDNumber");
            acc = new BankAccount(acctNo, lastname, firstname, taxid);
            ba.add(acc);
            
        }
        
        
        return ba;
    }
    
}
