
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void menuFornecedor(Connection conn, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n=== Menu Fornecedor ===");
            System.out.println("1 - Cadastrar Item");
            System.out.println("2 - Ver Lista de Itens Interessados por Clientes");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                System.out.print("Nome do item: ");
                String nome = scanner.nextLine();
                System.out.print("Tipo: ");
                String tipo = scanner.nextLine();
                System.out.print("Tamanho: ");
                String tamanho = scanner.nextLine();
                System.out.print("Marca: ");
                String marca = scanner.nextLine();
                System.out.print("Valor: ");
                double valor = scanner.nextDouble();
                scanner.nextLine();

                String sql = "INSERT INTO Item (nome, tipo, tamanho, marca, valor, fornecedor_id) VALUES (?, ?, ?, ?, ?, 1)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, nome);
                    stmt.setString(2, tipo);
                    stmt.setString(3, tamanho);
                    stmt.setString(4, marca);
                    stmt.setDouble(5, valor);
                    stmt.executeUpdate();
                    System.out.println("Item cadastrado!");
                }

            } else if (escolha == 2) {
                String sql = "SELECT Cliente.nome, Item.nome FROM Pedido INNER JOIN Cliente ON Pedido.cliente_id = Cliente.id INNER JOIN Item ON Pedido.item_id = Item.id";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    ResultSet rs = stmt.executeQuery();
                    System.out.println("\nItens interessados pelos clientes:");
                    while (rs.next()) {
                        System.out.println("Cliente: " + rs.getString(1) + " | Item: " + rs.getString(2));
                    }
                }

            } else if (escolha == 3) {
                break;
            }
        }
    }

    public static void menuCliente(Connection conn, Scanner scanner, String login) throws SQLException {
        int clienteId = buscarClienteId(conn, login);

        while (true) {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println("1 - Ver Itens Disponíveis");
            System.out.println("2 - Ver Meus Pedidos");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                String sql = "SELECT id, nome, tipo, tamanho, marca, valor FROM Item";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " | Tipo: " + rs.getString(3) +
                                " | Tamanho: " + rs.getString(4) + " | Marca: " + rs.getString(5) + " | Valor: R$" + rs.getDouble(6));
                    }
                }

                System.out.print("Digite o ID do item para comprar ou 0 para cancelar: ");
                int idItem = scanner.nextInt();
                scanner.nextLine();

                if (idItem != 0) {
                    String sqlInsert = "INSERT INTO Pedido (cliente_id, item_id) VALUES (?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                        stmt.setInt(1, clienteId);
                        stmt.setInt(2, idItem);
                        stmt.executeUpdate();
                        System.out.println("Pedido realizado com sucesso!");
                    }
                }

            } else if (escolha == 2) {
                String sql = "SELECT Item.nome, Item.valor FROM Pedido INNER JOIN Item ON Pedido.item_id = Item.id WHERE Pedido.cliente_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, clienteId);
                    ResultSet rs = stmt.executeQuery();
                    System.out.println("\nSeus pedidos:");
                    while (rs.next()) {
                        System.out.println("Item: " + rs.getString(1) + " | Valor: R$" + rs.getDouble(2));
                    }
                }

            } else if (escolha == 3) {
                break;
            }
        }
    }

    private static int buscarClienteId(Connection conn, String login) throws SQLException {
        String sql = "SELECT id FROM Cliente WHERE login = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
}
