package Servlets;

import CrudServices.DeveloperCrudService;
import Entities.Developer;
import lombok.SneakyThrows;


import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditDev")
public class EditDevServlet extends HttpServlet {
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Update Developer</h1>");
        String sid = request.getParameter("id");
        long id = Integer.parseInt(sid);

        Developer developer = DeveloperCrudService.getById(id);

        out.print("<form action='Edit2Dev' method='post'>");
        out.print("<table>");
        out.print("<tr><td></td><td><input type='hidden' name='id' value='" + Objects.requireNonNull(developer).getID()
                + "'/></td></tr>");
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + developer.getName()
                + "'/></td></tr>");
        out.print("<tr><td>Password:</td><td><input type='text' name='sex' value='" + developer.getSex()
                + "'/> </td></tr>");
        out.print("<tr><td>Email:</td><td><input type='number' name='salary' value='" + developer.getSalary()
                + "'/></td></tr>");
        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.close();
    }
}  