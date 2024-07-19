package bhirud.SERVLETS;

import bhirud.CLASSES.BankAccount;
import bhirud.INTERFACES.AccountUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author : Rajeshwari Bhirud 
 * Date : 04/14/2024
 * 
 * This servlet is used to make withdrawal from both Checking and Savings account
 */
public class MakeWithdrawal extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String accNo = request.getParameter("accNo");
        double amount = Double.parseDouble(request.getParameter("withdrawalAmt"));
        String accType = request.getParameter("withdrawalTo");
        
        BankAccount ba = AccountUtility.buildAccountFromQuery(accNo);
        
        System.out.println("accno : "+ba.getAccountNumber()+ "  amount"+ amount);
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MakeWithdrawal</title>");            
            out.println("</head>");
            out.println("<body>");
            if(accType.equals("Checking")){
                String htmlStr = ba.withdrawFromChecking(amount);
                out.print(htmlStr);
            
            }else if(accType.equals("Savings")){
                String htmlStr = ba.withdrawFromSavings(amount);
                out.print(htmlStr);
            
            }else{
                out.println("<h2> Enter either Checking or Savings</h2>");
            }
            
            out.println("<h1> WITHDRAWAL REQUEST CONFIRMED</h1>");
            out.print("<div>");
            out.print("<button id=\"withdrawalMenu\" onclick=\"window.location.href = 'Withdrawal.html'\" value=\"withdrawalMenu\">Go To Withdrawal Menu</button>");
            out.print("</div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MakeWithdrawal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MakeWithdrawal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
