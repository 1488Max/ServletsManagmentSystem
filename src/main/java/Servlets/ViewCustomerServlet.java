package Servlets;

import CrudServices.CustomerCrudService;
import CrudServices.DeveloperCrudService;
import Entities.Customer;
import Entities.Developer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewCustomer")
public class ViewCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='AddCustomer'>Add New Customer</a>");
        out.println("<h1>Customer List</h1>");

        List<Customer> list = null;
        try {
            list = CustomerCrudService.getAllDevelopers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Surname</th><th>Edit</th><th>Delete</th></tr>");
        for (Customer customer : list) {
            out.print("<tr><td>" + customer.getID() + "</td><td>" + customer.getName() + "</td><td>"
                    + customer.getSurname() + "</td> <td><a href='EditCustomer?id=" + customer.getID()
                    + "'>edit</a></td> <td><a href='DeleteCustomer?id="
                    + customer.getID() + "'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}