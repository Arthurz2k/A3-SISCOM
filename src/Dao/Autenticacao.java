package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autenticacao {

    public static boolean autenticarCliente(Connection conn, String login, String senha) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE login = ? AND senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public static boolean autenticarFornecedor(Connection conn, String login, String senha) throws SQLException {
        String sql = "SELECT * FROM Fornecedor WHERE login = ? AND senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}
