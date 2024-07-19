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
 *@author : Rajeshwari Bhirud 
 * Date : 04/06/2024
 * 
 * Servlet class to find a account
 */


public class ShowAccount extends HttpServlet  implements AccountUtility{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accNo = request.getParameter("findAccNo");
        BankAccount ba = AccountUtility.buildAccountFromQuery(accNo);
        
        
        String accountInfo = ba.showAccount();
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowAccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println( accountInfo );         
            out.print("<br><br>");
            out.print("<div>");
            out.print("<button id=\"mainMenu\" onclick=\"window.location.href = 'index.html'\" value=\"mainMenu\">Go To Main Menu</button>");
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
            Logger.getLogger(ShowAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ShowAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
