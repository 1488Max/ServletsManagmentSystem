package Servlets;

import CrudServices.DeveloperCrudService;
import Entities.Developer;
import lombok.SneakyThrows;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebServlet("/addDeveloper")
public class AddDeveloper extends HttpServlet {
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
        String sex = request.getParameter("sex");
        int salary = Integer.parseInt(request.getParameter("salary"));

        Developer developer = new Developer(name,sex,salary);



        int status = DeveloperCrudService.create(developer);
        if(status>0){
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }
}


