package CrudServices;

import Connection.ConnectionToDB;
import Entities.Developer;
import Entities.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectCrudService {
    private static PreparedStatement getDevelopers;
    static Connection connection = ConnectionToDB.getConnection();
    private static PreparedStatement readSt;
    private static PreparedStatement createSt;
    private static PreparedStatement getSumOfSalarySt;
    private static PreparedStatement getAllProjects;
    private static PreparedStatement getCountOfDevelopers;
    private static PreparedStatement updateSt;
    private static PreparedStatement deleteSt;

    static {
        {
            try {
                createSt = connection.prepareStatement("INSERT INTO PROJECT (name, TIME_OF_CREATION, COMPANY_ID,CUSTOMER_ID)" +
                        " VALUES (?, ?, ?,?)");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static {
        try {
            readSt = connection
                    .prepareStatement("SELECT id, TIME_OF_CREATION, CUSTOMER_ID, COMPANY_ID FROM PROJECT WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            getSumOfSalarySt = connection.prepareStatement("""
                    SELECT sum(SALARY)
                    from DEVELOPER
                             join DEVELOPER_PROJECT on DEVELOPER.ID = DEVELOPER_PROJECT.DEVELOPER_ID
                             join PROJECT  on DEVELOPER_PROJECT.PROJECT_ID = PROJECT.ID
                    where PROJECT_ID = ?
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            getDevelopers = connection.prepareStatement("""
                    SELECT DEVELOPER.*
                    from DEVELOPER
                             join DEVELOPER_PROJECT on DEVELOPER.ID = DEVELOPER_PROJECT.DEVELOPER_ID
                             join PROJECT  on DEVELOPER_PROJECT.PROJECT_ID = PROJECT.ID
                    where PROJECT_ID = ?
                                  
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            getCountOfDevelopers = connection.prepareStatement("""
                    SELECT Project.*, COUNT(DEVELOPER.id)
                                                                      FROM project
                                                                      JOIN developer_project ON developer_project.project_id = project.id
                                                                      JOIN developer ON developer.id = developer_project.developer_id
                                                                      where PROJECT_ID = ?
                                                                      GROUP BY project.id""");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            getAllProjects = connection
                    .prepareStatement("SELECT id, TIME_OF_CREATION, CUSTOMER_ID, COMPANY_ID, NAME FROM PROJECT");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            updateSt = connection
                    .prepareStatement("UPDATE project SET project.name = ? WHERE project.id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            deleteSt = connection.
                    prepareStatement("DELETE from project WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int create(Project project) throws SQLException {
        createSt.setString(1, project.getName());
        createSt.setString(2, project.getTimeOfCreation().toString());
        createSt.setLong(3, project.getCustomerId());
        createSt.setLong(4, project.getCompanyId());
        return createSt.executeUpdate();

    }

    public Project getById(long id) throws SQLException {
        readSt.setLong(1, id);

        ResultSet rs = readSt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        Project result = new Project(rs.getString("name"),rs.getString("time_of_creation"),
                rs.getLong("customer_id"),rs.getLong("company_id"));
        result.setID(id);

        return result;
    }

    public static int getSumOfSalaryByProjectID(int projectID) throws SQLException {

        getSumOfSalarySt.setLong(1, projectID);
        ResultSet resultSet = getSumOfSalarySt.executeQuery();
        if (!resultSet.next()) {
            System.out.println("There is no developers in this project");
        }
        int sum = resultSet.getInt("SUM(SALARY)");

        return sum;
    }

    public static long getCountOfDevByProject(long id) throws SQLException {
        getCountOfDevelopers.setLong(1, id);
        ResultSet resultSet = getCountOfDevelopers.executeQuery();
        if (!resultSet.next()) {
            return -1;
        }
        return resultSet.getInt("COUNT(DEVELOPER.ID)");

    }

    public static List<Project> getAllProjects() throws SQLException {
        ResultSet resultSet = getAllProjects.executeQuery();

        List<Project> result = new ArrayList<Project>();
        while ((resultSet.next())) {
            Project project = new Project(resultSet.getString("Name"),
                    resultSet.getString("time_of_creation"), resultSet.getLong("customer_id"),
                    resultSet.getLong("company_id"));
            result.add(project);
        }

        return result;
    }

    public static List<Developer> getDeveloperByProject(int projectID) throws SQLException {
        getDevelopers.setLong(1, projectID);
        ResultSet resultSet = getDevelopers.executeQuery();

        List<Developer> result = new ArrayList<Developer>();
        while (resultSet.next()) {
            Developer developer = new Developer(resultSet.getString("name"),
                    resultSet.getString("sex"), resultSet.getInt("salary"));
            result.add(developer);
        }
        return result;
    }

    public static void updateNameByID(long id, String inputInfo) throws SQLException {

        updateSt.setString(1, inputInfo);
        updateSt.setLong(2, id);

        updateSt.executeUpdate();
    }

    public static void deleteByID(long id) throws SQLException {
        deleteSt.setLong(1, id);

        deleteSt.executeUpdate();
    }
}