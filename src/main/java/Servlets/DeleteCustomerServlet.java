package Servlets;

import CrudServices.CustomerCrudService;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DeleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String sid=request.getParameter("id");
        long id=Integer.parseInt(sid);
        try {
            CustomerCrudService.deleteByID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("ViewCustomer");
    }
}