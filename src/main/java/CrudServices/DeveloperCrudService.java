package CrudServices;

import Entities.Developer;
import Connection.ConnectionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperCrudService {


    static Connection connection = ConnectionToDB.getConnection();
    private static final PreparedStatement createSt;
    private static final PreparedStatement readSt;
    private static final PreparedStatement getDevByTechnology;
    private static final PreparedStatement getDevBySKill;
    private static final PreparedStatement updateSt;
    private static final PreparedStatement deleteSt;
    private static final PreparedStatement getAllDevelopers;


    static {
        try {
             getAllDevelopers = connection
                    .prepareStatement("SELECT id, name, sex, salary FROM developer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            createSt = connection
                    .prepareStatement("INSERT INTO developer (name, sex, salary) VALUES (?, ?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            readSt = connection
                    .prepareStatement("SELECT id, name, sex, salary FROM developer WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            getDevByTechnology = connection.prepareStatement("""
                                    SELECT DEVELOPER.*
                                    from DEVELOPER
                                             join DEVELOPER_SKILL on DEVELOPER.ID = DEVELOPER_SKILL.DEVELOPER_ID
                                             join SKILL  on DEVELOPER_SKILL.SKILL_ID = SKILL.ID
                                    where SKILL.TECHNOLOGY = ?
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            getDevBySKill = connection.prepareStatement("""
                                    SELECT DEVELOPER.*
                                    from DEVELOPER
                                             join DEVELOPER_SKILL on DEVELOPER.ID = DEVELOPER_SKILL.DEVELOPER_ID
                                             join SKILL  on DEVELOPER_SKILL.SKILL_ID = SKILL.ID
                                    where SKILL.SKILL = ?
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            updateSt = connection
                    .prepareStatement("UPDATE developer SET developer.name = ?, developer.sex = ?," +
                            " developer.salary= ? WHERE developer.id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            deleteSt = connection.
                    prepareStatement("DELETE from developer WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int create(Developer developer) throws SQLException {
        createSt.setString(1, developer.getName());
        createSt.setString(2, developer.getSex());
        createSt.setLong(3, developer.getSalary());

        int result = createSt.executeUpdate();

        return result;
    }

    public static List<Developer> getJavaDevelopers() throws SQLException {
        getDevByTechnology.setString(1, "Java");
        ResultSet resultSet = getDevByTechnology.executeQuery();

        List<Developer> result = new ArrayList<Developer>();
        while (resultSet.next()) {
            Developer developer = new Developer(resultSet.getString("name"),
                    resultSet.getString("sex"), resultSet.getInt("salary"));
            result.add(developer);
        }
        return result;
    }


    public static List<Developer> getMiddleDevelopers() throws SQLException {
        getDevBySKill.setString(1, "Middle");
        ResultSet resultSet = getDevBySKill.executeQuery();
        List<Developer> result = new ArrayList<Developer>();
        while (resultSet.next()) {
            Developer developer = new Developer(resultSet.getString("name"),
                    resultSet.getString("sex"), resultSet.getInt("salary"));
            result.add(developer);
        }
        return result;
    }


    public static Developer getById(long id) throws SQLException {
        readSt.setLong(1, id);

        ResultSet rs = readSt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        String name = rs.getString("name");
        String sex = rs.getString("sex");
        int salary = rs.getInt("salary");

        Developer result = new Developer(name, sex, salary);
        result.setId(id);

        return result;
    }

    public static int updateDeveloper(Developer developer) throws SQLException {

        updateSt.setString(1, developer.getName());
        updateSt.setString(2, developer.getSex());
        updateSt.setInt(3, developer.getSalary());
        updateSt.setLong(4, developer.getId());

        return updateSt.executeUpdate();
    }

    public static void deleteByID(long id) throws SQLException {
        deleteSt.setLong(1, id);
        deleteSt.executeUpdate();

    }
    public static List<Developer> getAllDevelopers() throws SQLException {
        ResultSet resultSet = getAllDevelopers.executeQuery();

        List<Developer> result = new ArrayList<Developer>();
        while ((resultSet.next())) {
            Developer developer = new Developer(resultSet.getLong("id"),resultSet.getString("name"),
                    resultSet.getString("sex"), resultSet.getInt("salary"));
            result.add(developer);
        }

        return result;
    }


}