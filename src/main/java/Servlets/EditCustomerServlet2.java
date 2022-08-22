package Servlets;

import CrudServices.CustomerCrudService;
import CrudServices.DeveloperCrudService;
import Entities.Customer;
import Entities.Developer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditCustomer2")
public class EditCustomerServlet2 extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        long id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        Customer customer = new Customer(id,name,surname);
        int status = CustomerCrudService.updateCustomer(customer);
        if (status > 0) {
            out.println("Your record was successfully saved");
            response.sendRedirect("ViewCustomer");
        } else {
            out.println("Sorry! unable to update record");
        }

        out.close();
    }

}