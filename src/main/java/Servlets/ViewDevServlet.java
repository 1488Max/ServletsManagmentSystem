package Servlets;

import CrudServices.DeveloperCrudService;
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

@WebServlet("/ViewDev")
public class ViewDevServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='index.jsp'>Add New Developer</a>");
        out.println("<h1>Developers List</h1>");

        List<Developer> list = null;
        try {
            list = DeveloperCrudService.getAllDevelopers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Sex</th><th>Salary</th><th>Edit</th><th>Delete</th></tr>");
        for (Developer developer : list) {
            out.print("<tr><td>" + developer.getID() + "</td><td>" + developer.getName() + "</td><td>"
                    + developer.getSex() + "</td> <td>" + developer.getSalary() + "</td><td><a href='EditDev?id="
                    + developer.getID() + "'>edit</a></td> <td><a href='DeleteDev?id=" + developer.getID()
                    + "'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}  