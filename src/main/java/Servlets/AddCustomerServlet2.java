package Servlets;

import CrudServices.CustomerCrudService;
import CrudServices.DeveloperCrudService;
import Entities.Customer;
import Entities.Developer;
import lombok.SneakyThrows;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebServlet("/AddCustomer2")
public class AddCustomerServlet2 extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    // override the supertype method post
    @SneakyThrows
    @Override

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();


        String name = request.getParameter("name");
        String surname = request.getParameter("surname");


        Customer customer = new Customer(name,surname);


        int status = CustomerCrudService.create(customer);
        if(status>0){
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }
}


