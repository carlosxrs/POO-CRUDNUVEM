package crudnuvem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Aluno: Carlos Eduardo Rodrigues de Sousa
 * RA: 0024303
 * Atividade: POO / CRUD em Nuvem (Railway)
 */
public class ConexaoCrud {

    // PREENCHIDO COM OS DADOS DO SEU RAILWAY:
    private static final String HOST = "shortline.proxy.rlwy.net";
    private static final String PORT = "38537";
    private static final String DATABASE = "railway";
    private static final String USER = "root";
    private static final String PASSWORD = "pAVsQhTfTYqJabMtmvMmcLdhEWLkiXxG";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false&serverTimezone=UTC";

    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado com sucesso ao banco de dados no Railway!");
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Testa a conexão ao rodar a classe
        conectar();
    }
}

    public static void main(String[] args) {
        // Testa a conexão e as operações básicas
        conectar();
        
        // Exemplo de uso (descomente se já tiver a tabela 'usuarios' criada no Railway):
        // inserir("Carlos Eduardo", "carlos@email.com");
        // listar();
    }
}
