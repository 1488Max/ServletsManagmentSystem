package CrudServices;

import Connection.ConnectionToDB;
import Entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerCrudService {
    static Connection connection = ConnectionToDB.getConnection();
    private static PreparedStatement createSt;
    private static PreparedStatement readSt;
    private static PreparedStatement updateSt;
    private static PreparedStatement deleteSt;
    private static final PreparedStatement getAllCustomers;


    static {
        try {
            getAllCustomers = connection
                    .prepareStatement("SELECT id, name, surname FROM customer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            createSt = connection
                    .prepareStatement("INSERT INTO CUSTOMER (name, SURNAME) VALUES (?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            readSt = connection
                    .prepareStatement("SELECT id, name, surname FROM CUSTOMER WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            updateSt = connection
                    .prepareStatement("UPDATE CUSTOMER SET customer.name = ?, customer.surname =? WHERE customer.id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            deleteSt = connection.
                    prepareStatement("DELETE from customer WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int create(Customer customer) throws SQLException {
        createSt.setString(1, customer.getName());
        createSt.setString(2, customer.getSurname());
        int result = createSt.executeUpdate();

        return result;
    }

    public static Customer getById(long id) throws SQLException {
        readSt.setLong(1, id);

        ResultSet rs = readSt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        String name = rs.getString("name");
        String surname = rs.getString("surname");


        Customer result = new Customer(name, surname);
        result.setID(id);

        return result;
    }

    public static int updateCustomer(Customer customer) throws SQLException {

        updateSt.setString(1, customer.getName());
        updateSt.setString(2, customer.getSurname());
        updateSt.setLong(3, customer.getID());
         return updateSt.executeUpdate();

    }

    public static void deleteByID(long id) throws SQLException {
        deleteSt.setLong(1, id);

        deleteSt.executeUpdate();
    }

    public static List<Customer> getAllDevelopers() throws SQLException {
        ResultSet resultSet = getAllCustomers.executeQuery();

        List<Customer> result = new ArrayList<Customer>();
        while ((resultSet.next())) {
            Customer customer = new Customer(resultSet.getLong("id"),resultSet.getString("name"),
                    resultSet.getString("surname"));
            result.add(customer);
        }

        return result;
    }

}