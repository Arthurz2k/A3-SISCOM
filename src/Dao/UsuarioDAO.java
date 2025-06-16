package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public static void cadastrarCliente(Connection conn, String nome, String login, String senha) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, login, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        }
    }

    public static void cadastrarFornecedor(Connection conn, String nome, String login, String senha) throws SQLException {
        String sql = "INSERT INTO Fornecedor (nome, login, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.executeUpdate();
            System.out.println("Fornecedor cadastrado com sucesso!");
        }
    }

    public static boolean alterarSenhaCliente(Connection conn, String login, String novaSenha) throws SQLException {
        String sql = "UPDATE Cliente SET senha = ? WHERE login = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setString(2, login);
            int linhas = stmt.executeUpdate();
            return linhas > 0;
        }
    }

    public static boolean alterarSenhaFornecedor(Connection conn, String login, String novaSenha) throws SQLException {
        String sql = "UPDATE Fornecedor SET senha = ? WHERE login = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setString(2, login);
            int linhas = stmt.executeUpdate();
            return linhas > 0;
        }
    }
}
