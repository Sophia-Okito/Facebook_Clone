package com.sophia.Facebook_Clone.DAO;

import com.sophia.Facebook_Clone.model.User;
import java.sql.*;

public class UserDAO {
    final String getUserByEmailSql = "SELECT * FROM users WHERE email=?;";
    final String createNewUserSql = "Insert Into users (firstName,lastName, email, password, gender, DateOfBirth) VALUE(?,?,?,?,?,?);";

    // method to get a single user from the database
    public User getUserByEmail(String emailAddress)  {
        User user = null;
        try(Connection connection =  DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getUserByEmailSql);
            preparedStatement.setString(1, emailAddress);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()){
                int userID = resultSet.getInt("userID");
                String firstName =  resultSet.getString("firstName");
                String lastName =  resultSet.getString("lastName");
                String email =  resultSet.getString("email");
                String password =  resultSet.getString("password");
                String gender =  resultSet.getString("gender");
                String DateOfBirth = resultSet.getString("DateOfBirth");
                String Status =  resultSet.getString("Status");
                Timestamp lastLogin =  resultSet.getTimestamp("lastLoginDate");
                Timestamp dateCreated = resultSet.getTimestamp("dateCreated");
                // create a new user object
                user =  new User(userID,firstName,lastName,email,password,gender,DateOfBirth,
                        dateCreated, lastLogin, Status);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return user;

    }

    // method to add a new user to the database;
    public boolean createNewUser(User newUser) {
        try (Connection connection = DBConnection.getConnection()){
            // CHECK IF THE USER ALREADY EXISTS
            User userAlreadyExists =  getUserByEmail(newUser.getEmail());
            if (userAlreadyExists != null)
                return false;

            PreparedStatement preparedStatement = connection.prepareStatement(createNewUserSql);
            preparedStatement.setString(1,newUser.getFirstName());
            preparedStatement.setString(2,newUser.getLastName());
            preparedStatement.setString(3,newUser.getEmail());
            preparedStatement.setString(4,newUser.getPassword());
            preparedStatement.setString(5, newUser.getGender());
            preparedStatement.setString(6, newUser.getDateOfBirth());

            boolean createUser = preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
