package com.addUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "registerUser", value = "/registerUser")
public class registerUser extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello World!</h3>");

        String first_name=request.getParameter("first_name");
        String last_name=request.getParameter("last_name");
      int age= Integer.parseInt(request.getParameter("age"));
       String email=request.getParameter("email");
       String password=request.getParameter("pwd");
out.println(password);
        boolean value_added = addUserData(first_name,last_name,age,email,password);
        if ( value_added){
//            HttpSession httpSession = request.getSession();
out.println("done");
//            httpSession.setAttribute("emailId", user_email);
//            response.sendRedirect("welcome.jsp"); // Redirect to a welcome page on successful login
        } else {
//            response.sendRedirect("login.html"); // Redirect back to login page if authentication fails

            out.println("Invalid Credentials");
        }
    }

    private boolean addUserData(String first_name,String last_name,int age,String email,String password){
        java.lang.String jdbcURL = "jdbc:mariadb://localhost:3306/users";
        java.lang.String dbUser = "root";
        java.lang.String dbPassword = "root";
        boolean status = false;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement st = connection.createStatement();


String sql =("INSERT INTO user_details (fname, lname, age, email, password) " +
                 "VALUES ('" + first_name + "','" + last_name + "'," + age + ",'" + email + "','" + password + "')");


int rowsInserted =st.executeUpdate(sql);
            if (rowsInserted > 0) {
        System.out.println("User registered successfully!");
                status = true;}

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }}



