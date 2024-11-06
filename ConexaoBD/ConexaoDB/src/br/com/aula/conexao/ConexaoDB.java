package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    // Definindo parâmetros para a conexão
    private static final String URL = "jdbc:mysql://localhost:3306/aula_java_db";
    private static final String USUARIO = "root";
    private static final String SENHA = ""; // Coloque a senha do seu MySQL, se houver

    // Método para obter conexão com o banco
    public static Connection conectar() {
        Connection conexao = null;
        try {
            // Carregando o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tentando estabelecer conexão
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão realizada com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
        return conexao;
    }

    public static void main(String[] args) {
        // Testando a conexão
        conectar();
    }
}
