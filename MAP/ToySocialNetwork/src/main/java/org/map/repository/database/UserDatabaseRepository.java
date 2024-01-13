package org.map.repository.database;

import org.map.domain.User;
import org.map.repository.Repository;
import org.map.validator.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserDatabaseRepository implements Repository<Long, User> {

    private final String url;
    private final String username;
    private final String password;

    private final Validator<User> validator;


    public UserDatabaseRepository(String url, String username, String password, Validator<User> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }
    @Override
    public Optional<User> save(User entity) {

        if (entity == null)
            throw new IllegalArgumentException("Invalid Entity");

        validator.validate(entity);
        //The insert command
        String insertSQLStatement = "insert into users(first_name,last_name) values (?,?);";

        //Get the connection to database
        try(Connection connection = DriverManager.getConnection(url,username,password);
            //The SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQLStatement)) {
            //Set the string
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2,entity.getLastName());

            //Get the response from Database
            int response = preparedStatement.executeUpdate();
            return response==1 ? Optional.of(entity) : Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<User> findOne(Long id) {

        String selectSQLStatement = "select * from users where user_id=?;";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQLStatement)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Long idUser = resultSet.getLong("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                User theUser = new User(idUser,firstName,lastName);
                return Optional.of(theUser);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Iterable<User> findAll() {

        Set<User> users = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("select * from users;");
            ResultSet resultSet = statement.executeQuery()
        ) {

            while(resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                User user = new User(id,first_name,last_name);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<User> update(User entity) {
        String alterSQLStatement = "update users set first_name = ?, last_name = ? where user_id = ?;";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(alterSQLStatement)) {

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setLong(3, entity.getID());

            int response = preparedStatement.executeUpdate();
            return response == 1 ? Optional.of(entity) : Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<User> delete(Long id) {
        String deleteSQLStatement = "delete from users where user_id=?;";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQLStatement)) {
            preparedStatement.setLong(1, id);
            Optional<User> user = findOne(id);
            int response = 0;
            if(user.isPresent()) {
                response = preparedStatement.executeUpdate();
            }
            return response == 1 ? user : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
