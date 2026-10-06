package ru.init.fisd.dao;

import ru.init.fisd.entity.UserEntity;
import ru.init.fisd.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public UserEntity save(UserEntity user) {
        String sql = "INSERT INTO users (name, hash_pass, role) VALUES (?, ?, ?) RETURNING id";

        try(
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
                ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getHashPass());
            ps.setString(3, user.getRole());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setId(rs.getLong("id"));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserEntity findByUsername(String name) {
        String sql = "SELECT id, name, hash_pass, role FROM users WHERE name = ?";

        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UserEntity(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("hash_pass"),
                            rs.getString("role")
                    );
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error executing findByUsername", e);
        }
    }
}
