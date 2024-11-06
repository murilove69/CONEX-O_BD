package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InserirDados {
    public static void main(String[] args) {
        Connection conexao = ConexaoDB.conectar();
        if (conexao != null) {
            String sql = "INSERT INTO alunos (nome, idade) VALUES (?, ?)";
            try {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, "João Silva");
                stmt.setInt(2, 20);
                stmt.executeUpdate();

                // Inserir segundo registro
                stmt.setString(1, "Maria Souza");
                stmt.setInt(2, 22);
                stmt.executeUpdate();

                // Inserir terceiro registro
                stmt.setString(1, "Pedro Santos");
                stmt.setInt(2, 25);
                stmt.executeUpdate();

                System.out.println("Dados inseridos com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao inserir dados: " + e.getMessage());
            } finally {
                try {
                    if (conexao != null) conexao.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}
