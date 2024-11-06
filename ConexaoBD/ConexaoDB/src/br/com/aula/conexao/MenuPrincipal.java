package br.com.aula.conexao;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuPrincipal {
    public static void main(String[] args) {
        // Estabelecendo conexão com o banco de dados
        Connection conexao = ConexaoDB.conectar();
        if (conexao != null) {
            Scanner scanner = new Scanner(System.in);
            int opcao;
            do {
                // Exibindo menu principal
                System.out.println("=== Menu Principal ===");
                System.out.println("1. Inserir Aluno");
                System.out.println("2. Atualizar Aluno");
                System.out.println("3. Deletar Aluno");
                System.out.println("4. Ler Registros de Alunos");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha

                // Executando a ação conforme a escolha do usuário
                switch (opcao) {
                    case 1:
                        inserirAluno(conexao, scanner);
                        break;
                    case 2:
                        atualizarAluno(conexao, scanner);
                        break;
                    case 3:
                        deletarAluno(conexao, scanner);
                        break;
                    case 4:
                        lerDadosAlunos(conexao);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
            scanner.close();
        }
    }

    private static void inserirAluno(Connection conexao, Scanner scanner) {
        String sql = "INSERT INTO alunos (nome, idade) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            System.out.print("Digite o nome do aluno: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a idade do aluno: ");
            int idade = scanner.nextInt();

            // Definindo os valores dos parâmetros da query
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.executeUpdate(); // Executando a inserção no banco de dados
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    private static void atualizarAluno(Connection conexao, Scanner scanner) {
        String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?";
        try {
            System.out.print("Digite o ID do aluno que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            System.out.print("Digite o novo nome do aluno: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a nova idade do aluno: ");
            int idade = scanner.nextInt();

            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Definindo os valores dos parâmetros da query
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate(); // Executando a atualização no banco de dados
            if (rowsUpdated > 0) {
                System.out.println("Registro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
        }
    }

    private static void deletarAluno(Connection conexao, Scanner scanner) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try {
            System.out.print("Digite o ID do aluno que deseja deletar: ");
            int id = scanner.nextInt();

            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Definindo o valor do parâmetro da query
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate(); // Executando a exclusão no banco de dados
            if (rowsDeleted > 0) {
                System.out.println("Registro deletado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
        }
    }

    private static void lerDadosAlunos(Connection conexao) {
        String sql = "SELECT * FROM alunos";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); // Executando a consulta no banco de dados

            // Iterando sobre o resultado da consulta
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");

                System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler dados: " + e.getMessage());
        }
    }
}
