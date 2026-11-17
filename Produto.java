import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Aluno: Carlos Eduardo Rodrigues de Sousa
 * RA: 0024303
 * Atividade: POO / CRUD em Nuvem (Railway)
 */
public class Produto {

    public static void main(String[] args) {
        /*
		// conexao local
		String url = "mysql://root:pAVsQhTfTYqJabMtmvMmcLdhEWLkiXxG@mysql-oees.railway.internal:3306/railway";
		String usuariobanco = "root";
		String bancosenha = "pAVsQhTfTYqJabMtmvMmcLdhEWLkiXxG";
		*/

       
        String url = "mysql://root:pAVsQhTfTYqJabMtmvMmcLdhEWLkiXxG@shortline.proxy.rlwy.net:38537/railway";
        String usuariobanco = "root";
        String bancosenha = "pAVsQhTfTYqJabMtmvMmcLdhEWLkiXxG";

        Scanner sc = new Scanner(System.in);
        try {

            Connection conexao = DriverManager.getConnection(url, usuariobanco, bancosenha);
            System.out.println("Conectado ao banco na nuvem com sucesso!");

           
            String sqlTabela = "CREATE TABLE IF NOT EXISTS PRODUTO ("
                    + "CODIGO INT PRIMARY KEY, "
                    + "NOME VARCHAR(100), "
                    + "PRECO FLOAT)";
            conexao.createStatement().executeUpdate(sqlTabela);

            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n===== MENU PRODUTOS =====");
                System.out.println(" 1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar | 0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();

                switch (opcao) {
                case 1:
                    System.out.print("Código do produto: ");
                    int codigo = sc.nextInt();
                    sc.nextLine(); // Limpa o buffer

                    System.out.print("Nome do Produto: ");
                    String nome = sc.nextLine();

                    System.out.print("Preço do produto: ");
                    float preco = sc.nextFloat();

                    String sql = "INSERT INTO PRODUTO(CODIGO,NOME,PRECO) VALUES(?,?,?)";

                    PreparedStatement stmt = conexao.prepareStatement(sql);
                    stmt.setInt(1, codigo);
                    stmt.setString(2, nome);
                    stmt.setFloat(3, preco);
                    stmt.executeUpdate();
                    System.out.println("Registro inserido com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o código do produto: ");
                    int codigo2 = sc.nextInt();

                    String sql2 = "SELECT * FROM PRODUTO WHERE CODIGO = ?";
                    PreparedStatement stmt2 = conexao.prepareStatement(sql2);

                    stmt2.setInt(1, codigo2);
                    ResultSet rs = stmt2.executeQuery();
                    boolean achou = false;
                    while (rs.next()) {
                        achou = true;
                        System.out.println("Nome Produto: " + rs.getString("NOME") +
                                " | Preço: R$ " + rs.getFloat("PRECO") + 
                                " | Código: " + rs.getInt("CODIGO"));
                    }
                    if (!achou) {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o codigo do produto para atualizar o preço: ");
                    int codPesquisa = sc.nextInt();

                    System.out.print("Digite o novo preço do produto: ");
                    float novopreco = sc.nextFloat();

                    String sqlUpd = "UPDATE PRODUTO SET PRECO = ? WHERE CODIGO = ?";

                    PreparedStatement stmtUp = conexao.prepareStatement(sqlUpd);
                    stmtUp.setFloat(1, novopreco);
                    stmtUp.setInt(2, codPesquisa);

                    int linhaAfetada = stmtUp.executeUpdate();

                    if (linhaAfetada > 0) {
                        System.out.println("Preço Atualizado com Sucesso!");
                    } else {
                        System.out.println("Produto não localizado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o codigo do produto para deletar: ");
                    int coddelete = sc.nextInt();

                    String sqlDel = "DELETE FROM PRODUTO WHERE CODIGO = ?";

                    PreparedStatement stmtDele = conexao.prepareStatement(sqlDel);
                    stmtDele.setInt(1, coddelete);

                    int linhaAfetada2 = stmtDele.executeUpdate();

                    if (linhaAfetada2 > 0) {
                        System.out.println("Deletado com Sucesso!");
                    } else {
                        System.out.println("Produto não localizado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção Inválida!");
                }
            }

            conexao.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
