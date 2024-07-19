package bhirud.SERVLETS;

import bhirud.CLASSES.BankAccount;
import bhirud.INTERFACES.AccountUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author : Rajeshwari Bhirud 
 * Date : 04/22/2024
 * 
 * This servlet is used to show the use of dynamic query(accounts based on first letter when type in the text box)
 */
public class DynamicQuery extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            ArrayList<BankAccount> bankAccounts = AccountUtility.buildAllAccuntsDynamicQuery();
            String acctNo = request.getParameter("acctNo");
            
            StringBuilder output = new StringBuilder();
            
            if (acctNo != null && !acctNo.isEmpty()) {
                for (BankAccount account : bankAccounts) {
                    if (account.getAccountNumber().startsWith(acctNo)) {
                        output.append(account.toHTML_Summary());
                    }
                }
            }
                      
            response.getWriter().write(output.toString());
        } catch (ClassNotFoundException | SQLException e) {
            response.getWriter().write("Error occurred: " + e.getMessage());
        }
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DynamicQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DynamicQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
