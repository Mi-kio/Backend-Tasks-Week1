package com.demo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;



public class DemoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        out.println("<h3>Hello World!</h3>");

        String n=request.getParameter("email");
        String p=request.getParameter("pwd");
//console ->    System.out.println(n);
//        out.println(n);   out.println("<h3>Hello World!</h3>");
        if (n!= null && n.equalsIgnoreCase("n@gmail.com") && p != null && p.equalsIgnoreCase("admin")) {

            HttpSession httpSession = request.getSession();

            httpSession.setAttribute("emailId", n);
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }else{
            out.println("Invalid Credentials");
        }
}
}

//<!--${pageContext.request.contextPath}-->