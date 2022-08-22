package Servlets;

import CrudServices.CustomerCrudService;
import CrudServices.DeveloperCrudService;
import Entities.Customer;
import Entities.Developer;
import lombok.SneakyThrows;


import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditCustomer")
public class EditCustomerServlet extends HttpServlet {
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Update Customer</h1>");
        String sid = request.getParameter("id");
        long id = Integer.parseInt(sid);

        Customer customer = CustomerCrudService.getById(id);

        out.print("<form action='EditCustomer2' method='post'>");
        out.print("<table>");
        out.print("<tr><td></td><td><input type='hidden' name='id' value='" + Objects.requireNonNull(customer).getID()
                + "'/></td></tr>");
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + customer.getName()
                + "'/></td></tr>");
        out.print("<tr><td>Password:</td><td><input type='text' name='surname' value='" + customer.getSurname()
                + "'/> </td></tr>");

        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.close();
    }
}