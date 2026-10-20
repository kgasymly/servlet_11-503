package org.example.repository;

import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "kanan123@@";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb_11_503";

    static {
        try { Class.forName("org.postgresql.Driver"); }
        catch (ClassNotFoundException e) { throw new RuntimeException(e); }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }


    public int getUsersToAdd() {
        String sql = "SELECT users_to_add FROM settings LIMIT 1";
        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt("users_to_add");
        } catch (SQLException e) {
            throw new RuntimeException("getUsersToAdd: " + e.getMessage(), e);
        }
        return 1;
    }


    public void saveUsers(List<User> users) {
        String sql = "INSERT INTO users(name, surname, age, email, city, phone) VALUES (?,?,?,?,?,?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            c.setAutoCommit(false);
            for (User u : users) {
                ps.setString(1, u.getName());
                ps.setString(2, u.getSurname());
                if (u.getAge() != null) ps.setInt(3, u.getAge());
                else ps.setNull(3, Types.INTEGER);
                ps.setString(4, u.getEmail());
                ps.setString(5, u.getCity());
                ps.setString(6, u.getPhone());
                ps.addBatch();
            }
            ps.executeBatch();
            c.commit();
        } catch (SQLException e) {
            throw new RuntimeException("saveUsers: " + e.getMessage(), e);
        }
    }


    public List<User> selectByEmail(String email) { return selectByColumn("email", email); }
    public List<User> selectByCity(String city)   { return selectByColumn("city",  city);  }
    public List<User> selectByPhone(String phone) { return selectByColumn("phone", phone); }

    private List<User> selectByColumn(String column, String value) {
        List<User> result = new ArrayList<>();
        String sql = "SELECT id, name, surname, age, email, city, phone FROM users WHERE " + column + " = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(User.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .surname(rs.getString("surname"))
                            .age((Integer) rs.getObject("age"))
                            .email(rs.getString("email"))
                            .city(rs.getString("city"))
                            .phone(rs.getString("phone"))
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("selectBy" + column + ": " + e.getMessage(), e);
        }
        return result;
    }
}