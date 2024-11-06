package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LerDados {
    public static void main(String[] args) {
        Connection conexao = ConexaoDB.conectar();
        if (conexao != null) {
            String sql = "SELECT * FROM alunos";
            try {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");

                    System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao ler dados: " + e.getMessage());
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
