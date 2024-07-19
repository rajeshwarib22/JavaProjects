package bhirud.SERVLETS;

import bhirud.CLASSES.BankAccount;
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
 * Servlet class to build the account
 */

public class BuildAccount extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String accNo = request.getParameter("accNo");
        String lastName = request.getParameter("lname");
        String firstName = request.getParameter("fname");
        String taxID = request.getParameter("taxIDNo");
        double checkingBal = Double.parseDouble(request.getParameter("checkBalance"));
        double savingBal = Double.parseDouble(request.getParameter("savingBalance"));
        double savingInterestRate = Double.parseDouble(request.getParameter("savingInterestRate"));

        BankAccount ba = new BankAccount(accNo, lastName, firstName, taxID, checkingBal, savingBal, savingInterestRate);

        ba.displayAccountInfo();
        ba.toSQL();
        ba.writeToSQLFile();
        ba.writeToDatabase();
        
        String htmlFormat = ba.toHTML();

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuildAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(htmlFormat);
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
            Logger.getLogger(BuildAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BuildAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
