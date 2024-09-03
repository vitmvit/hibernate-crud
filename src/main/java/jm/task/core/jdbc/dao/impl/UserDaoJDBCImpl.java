package jm.task.core.jdbc.dao.impl;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.exception.ConnectionException;
import jm.task.core.jdbc.exception.OperationException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jm.task.core.jdbc.constant.Constant.*;

public class UserDaoJDBCImpl implements UserDao {

    private final Optional<Connection> connection;

    public UserDaoJDBCImpl() {
        this.connection = new Util().getConnection();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        if (connection.isPresent()) {
            try (PreparedStatement ps = connection.get().prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, name);
                ps.setString(2, lastName);
                ps.setByte(3, age);
                ps.executeUpdate();
                System.out.println("UserDaoJDBCImpl: " + name + " add to database");
            } catch (SQLException ex) {
                throw new OperationException("Save user error: " + ex);
            }
        } else {
            throw new ConnectionException(CONNECTION_EXCEPTION);
        }
    }

    @Override
    public void removeUserById(long id) {
        if (connection.isPresent()) {
            try (PreparedStatement ps = connection.get().prepareStatement(REMOVE_USER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, id);
                ps.executeUpdate();
                System.out.println("UserDaoJDBCImpl: remove user with id=" + id);
            } catch (SQLException ex) {
                throw new OperationException("Remove user error: " + ex);
            }
        } else {
            throw new ConnectionException(CONNECTION_EXCEPTION);
        }
    }

    @Override
    public List<User> getAllUsers() {
        if (connection.isPresent()) {
            try (PreparedStatement ps = connection.get().prepareStatement(GET_ALL_USERS)) {
                ResultSet rs = ps.executeQuery();
                List<User> userList = new ArrayList<>();
                while (rs.next()) {
                    User result = new User();
                    result.setId(rs.getLong("id"));
                    result.setName(rs.getString("name"));
                    result.setLastName(rs.getString("lastname"));
                    result.setAge(rs.getByte("age"));
                    userList.add(result);
                    System.out.println("UserDaoJDBCImpl: find user " + result);
                }
                return userList;
            } catch (SQLException ex) {
                throw new OperationException("Find users error: " + ex);
            }
        }
        throw new ConnectionException(CONNECTION_EXCEPTION);
    }

    @Override
    public void createUsersTable() {
        if (connection.isPresent()) {
            try (Statement statement = connection.get().createStatement()) {
                statement.executeUpdate(CREATE_TABLE);
                System.out.println("UserDaoJDBCImpl: create table");
            } catch (SQLException ex) {
                throw new OperationException("Create table error: " + ex);
            }
        } else {
            throw new ConnectionException(CONNECTION_EXCEPTION);
        }
    }

    @Override
    public void dropUsersTable() {
        if (connection.isPresent()) {
            try (Statement statement = connection.get().createStatement()) {
                statement.executeUpdate(DROP_TABLE);
                System.out.println("UserDaoJDBCImpl: drop table");
            } catch (SQLException ex) {
                throw new OperationException("Drop table error: " + ex);
            }
        } else {
            throw new ConnectionException(CONNECTION_EXCEPTION);
        }
    }

    @Override
    public void cleanUsersTable() {
        if (connection.isPresent()) {
            try (Statement statement = connection.get().createStatement()) {
                statement.executeUpdate(CLEAN_TABLE);
                System.out.println("UserDaoJDBCImpl: clean table");
            } catch (SQLException ex) {
                throw new OperationException("Clean table error: " + ex);
            }
        } else {
            throw new ConnectionException(CONNECTION_EXCEPTION);
        }
    }
}