/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class InsertUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	long startTime = System.nanoTime();
    	
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printOut = response.getWriter();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        // get user input
        String displayname = request.getParameter("display_name").trim();
        String firstname = request.getParameter("first_name").trim();
        String lastname = request.getParameter("last_name").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String confirm_password = request.getParameter("confirm_password").trim();
        
        try {
            // verify no empty field or null field
        	if (
            		displayname.equals(null) || displayname.isEmpty() || 
            		firstname.equals(null) || firstname.isEmpty() ||
            		lastname.equals(null) || lastname.isEmpty() || 
            		email.equals(null) || email.isEmpty() || 
            		password.equals(null) || password.isEmpty() || 
            		confirm_password.equals(null) || confirm_password.isEmpty()){
                request.setAttribute("error_message", "null or empty field are not allowed");
                request.getRequestDispatcher("/Registration.jsp").forward(request, response);
        	}else if(
        			CheckInjection.isJsInject(displayname) ||
        			CheckInjection.isJsInject(firstname) ||
        			CheckInjection.isJsInject(lastname) ||
        			CheckInjection.isJsInject(email) ||
        			CheckInjection.isJsInject(password) ||
        			CheckInjection.isJsInject(confirm_password)){
        		request.setAttribute("error_message", "Javascript Injection Detected!");
        		request.getRequestDispatcher("/Registration.jsp").forward(request, response);
            }else{
                // verify whether pass and confirm pass are the same
                if (password.equals(confirm_password)){
                    // create connection to mysql db
                    con = DBConnection.initializeDatabase();
                    
                    // insert user data into db
                    st = con.prepareStatement("insert into customer (display_name,first_name,last_name,email,password) values (?,?,?,?,?)");
                    
                    st.setString(1, displayname);
                    st.setString(2, firstname);
                    st.setString(3, lastname);
                    st.setString(4, email);
                    st.setString(5, password);
                    
                    st.executeUpdate();
                    
                    // show successfull registration info and next step for user
                    request.setAttribute("success_message", "you have successfully registered, go to login page");
                    request.getRequestDispatcher("/Registration.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("error_message", "password mismatch");
                    request.getRequestDispatcher("/Registration.jsp").forward(request, response);
                }
            }
            
        }
        
        catch(IOException | ClassNotFoundException | SQLException | ServletException err){
            printOut.println(err.getMessage());
        }
        
        finally {
            try{
                // close result set object
                if (rs != null){
                    rs.close();    
                }
        
                // close prepared statement object
                if (st != null){
                    st.close();                    
                }
                         
                // close db connection
                if (con != null){
                    con.close();   
                }
            }
            
            catch (SQLException err){
                printOut.println(err.getMessage());    
            }
        }
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println(duration);
    }
 
}
