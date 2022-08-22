package Servlets;

import CrudServices.DeveloperCrudService;
import Entities.Developer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Edit2Dev")
public class Edit2DevServlet extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        long id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        int salary = Integer.parseInt(request.getParameter("salary"));

        Developer developer = new Developer(id, name, sex, salary);
        int status = DeveloperCrudService.updateDeveloper(developer);
        if (status > 0) {
            response.sendRedirect("ViewDev");
        } else {
            out.println("Sorry! unable to update record");
        }

        out.close();
    }

}  