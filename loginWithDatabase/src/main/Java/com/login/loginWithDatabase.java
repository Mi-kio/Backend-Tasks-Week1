package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginWithDatabase extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//      out.println("<h3>Hello World!</h3>");

        java.lang.String user_email=request.getParameter("email");
        java.lang.String user_password=request.getParameter("pwd");

        boolean isValidUser = authenticateUser(user_email, user_password);

        if (isValidUser) {
            HttpSession httpSession = request.getSession();

            httpSession.setAttribute("emailId", user_email);
            response.sendRedirect("welcome.jsp"); // Redirect to a welcome page on successful login
        } else {
//            response.sendRedirect("login.html"); // Redirect back to login page if authentication fails

            out.println("Invalid Credentials");
        }


    }

    private boolean authenticateUser(String user_email, String user_password){
        //create connection
        java.lang.String jdbcURL = "jdbc:mariadb://localhost:3306/users";
        java.lang.String dbUser = "root";
        java.lang.String dbPassword = "root";
        boolean isValid = false;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

//           String sql = ("select * from user where email='" + user_email + "' and password='" +user_password+"'");
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, user_email);
//            statement.setString(2, user_password);

            Statement st = connection.createStatement();
            ResultSet result;
            result= st.executeQuery("select * from user_details where email='" + user_email + "' and password='" +user_password+"'");

//            ResultSet result = st.executeQuery();
            if (result.next()) {
                System.out.println("User Authenticated");
                isValid = true; // User exists, authentication successful
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
    }


