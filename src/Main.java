
import Dao.Autenticacao;
import Dao.UsuarioDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_pedidos", "siscom_user", "123456");
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("\n=== MENU INICIAL ===");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar Novo Usuário");
            System.out.println("3 - Alterar Senha");
            System.out.print("Escolha uma opção: ");
            int escolhaInicial = scanner.nextInt();
            scanner.nextLine();

            if (escolhaInicial == 1) {
                System.out.print("Você é Cliente (C) ou Fornecedor (F)? ");
                String tipoUsuario = scanner.nextLine();

                System.out.print("Login: ");
                String login = scanner.nextLine();

                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                boolean autenticado = false;

                if (tipoUsuario.equalsIgnoreCase("C")) {
                    autenticado = Autenticacao.autenticarCliente(conn, login, senha);
                    if (autenticado) {
                        Menu.menuCliente(conn, scanner, login);
                    } else {
                        System.out.println("Falha na autenticação do Cliente.");
                    }
                } else if (tipoUsuario.equalsIgnoreCase("F")) {
                    autenticado = Autenticacao.autenticarFornecedor(conn, login, senha);
                    if (autenticado) {
                        Menu.menuFornecedor(conn, scanner);
                    } else {
                        System.out.println("Falha na autenticação do Fornecedor.");
                    }
                }

            } else if (escolhaInicial == 2) {
                System.out.print("Cadastrar Cliente (C) ou Fornecedor (F)? ");
                String tipoCadastro = scanner.nextLine();

                System.out.print("Nome: ");
                String nome = scanner.nextLine();

                System.out.print("Login: ");
                String novoLogin = scanner.nextLine();

                System.out.print("Senha: ");
                String novaSenha = scanner.nextLine();

                if (tipoCadastro.equalsIgnoreCase("C")) {
                    UsuarioDAO.cadastrarCliente(conn, nome, novoLogin, novaSenha);
                } else if (tipoCadastro.equalsIgnoreCase("F")) {
                    UsuarioDAO.cadastrarFornecedor(conn, nome, novoLogin, novaSenha);
                }

            } else if (escolhaInicial == 3) {
                System.out.print("Alterar senha de Cliente (C) ou Fornecedor (F)? ");
                String tipoAlteracao = scanner.nextLine();

                System.out.print("Login: ");
                String loginAlvo = scanner.nextLine();

                System.out.print("Nova Senha: ");
                String novaSenha = scanner.nextLine();

                boolean sucesso = false;

                if (tipoAlteracao.equalsIgnoreCase("C")) {
                    sucesso = UsuarioDAO.alterarSenhaCliente(conn, loginAlvo, novaSenha);
                } else if (tipoAlteracao.equalsIgnoreCase("F")) {
                    sucesso = UsuarioDAO.alterarSenhaFornecedor(conn, loginAlvo, novaSenha);
                }

                if (sucesso) {
                    System.out.println("Senha alterada com sucesso!");
                } else {
                    System.out.println("Login não encontrado!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
