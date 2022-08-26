package Servlets;

import CrudServices.DeveloperCrudService;
import Entities.Developer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class DevServlet extends HttpServlet {


    public void init() {
        DeveloperCrudService developerCrudService = new DeveloperCrudService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertDeveloper(request, response);
                    break;
                case "/delete":
                    deleteDeveloper(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateDeveloper(request, response);
                    break;
                default:
                    listDeveloper(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDeveloper(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Developer> listDeveloper = DeveloperCrudService.getAllDevelopers();
        request.setAttribute("listDeveloper", listDeveloper);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewDevelopers.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Developers-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Developer developer = DeveloperCrudService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Developers-form.jsp");
        request.setAttribute("developer", developer);
        dispatcher.forward(request, response);

    }

    private void insertDeveloper(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        int salary = Integer.parseInt(request.getParameter("salary"));
        Developer developer = new Developer(name,sex,salary);
        DeveloperCrudService.create(developer);
        response.sendRedirect("ViewDevelopers.jsp");
    }

    private void updateDeveloper(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        int salary = Integer.parseInt(request.getParameter("salary"));

        Developer developer = new Developer(id,name,sex,salary);
        DeveloperCrudService.updateDeveloper(developer);
        response.sendRedirect("ViewDevelopers.jsp");
    }

    private void deleteDeveloper(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeveloperCrudService.deleteByID(id);
        response.sendRedirect("ViewDevelopers.jsp");

    }
}