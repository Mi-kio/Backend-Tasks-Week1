<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ page import="jakarta.servlet.*" %>
<%@ page import="jakarta.servlet.http.*" %>


<html>

<head>
<title> show user details </title>
</head>

<body>
<table border="1.0">
<tr>
<th>id</th>
<th>fname</th>
<th>lname</th>
<th>age</th>
<th>email</th>
<th>password</th>
</tr>

<%
String JdbcUrl = "jdbc:mariadb://localhost:3306/users";
String username = "root";
String password = "root";
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
%>

<%
try{
  Class.forName("org.mariadb.jdbc.Driver");
  con = DriverManager.getConnection(JdbcUrl, username, password);

  if(con!=null){
  out.println("successfully connected");

   stmt = con.createStatement();
   String query = "SELECT * FROM user_details";
   rs = stmt.executeQuery(query);
   while (rs.next()) {
 %>
     <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("fname") %></td>
        <td><%= rs.getString("lname") %></td>
        <td><%= rs.getInt("age") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getString("password") %></td>
     </tr>
 <%
 }
}}
catch(Exception e){
   out.println( e.getMessage());
}
 finally {
         try {
            if (con != null) {
                con.close();
            }
            if(stmt!=null){
            con.close();
            }
            }
         catch (SQLException e) { }
     }
%>

</table>

</body>
</html>
