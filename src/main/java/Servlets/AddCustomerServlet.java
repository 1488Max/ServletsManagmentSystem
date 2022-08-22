package Servlets;


import lombok.SneakyThrows;


import java.io.PrintWriter;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCustomer")
public class AddCustomerServlet extends HttpServlet {
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<a href='index.jsp'>Add New Developer</a>");
        out.println("<h1>Create Customer</h1>");


        out.print("<form action='AddCustomer2' method='post'>");
        out.print("<table>");
        out.print("<tr><td></td><td><input type='hidden' name='id' /></td></tr>");
        out.print("<tr><td>Name:</td><td><input type='text' name='name' /></td></tr>");
        out.print("<tr><td>Surname:</td><td><input type='text' name='surname' /></td></tr>");
        out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");
        out.print("</table>");
        out.print("</form>");
        out.println("<a href='ViewCustomer'>View customers</a>");
        out.close();
    }
}