import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConexaoDB {
    private static final String URL_CONEXAO = "jdbc:mysql://localhost:3306/farmacia_db"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO_BANCO = "root";
    private static final String SENHA_BANCO = carregarSenha();

    private static String carregarSenha() {
        String senhaAmbiente = System.getenv("FARMACIA_DB_PASSWORD");
        if (senhaAmbiente != null && !senhaAmbiente.isEmpty()) {
            return senhaAmbiente;
        }

        Path caminhoArquivo = Paths.get("db.properties");
        if (!Files.exists(caminhoArquivo)) {
            throw new IllegalStateException("Defina FARMACIA_DB_PASSWORD ou crie o arquivo db.properties.");
        }

        Properties configuracoes = new Properties();
        String senhaConfigurada;
        try (InputStream fluxoEntrada = Files.newInputStream(caminhoArquivo)) {
            configuracoes.load(fluxoEntrada);
            senhaConfigurada = configuracoes.getProperty("FARMACIA_DB_PASSWORD");
        } catch (IOException erroLeitura) {
            throw new IllegalStateException("Nao foi possivel ler o arquivo db.properties.", erroLeitura);
        }

        if (senhaConfigurada == null || senhaConfigurada.isEmpty()) {
            throw new IllegalStateException("A propriedade FARMACIA_DB_PASSWORD nao foi configurada.");
        }
        return senhaConfigurada;
    }

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL_CONEXAO, USUARIO_BANCO, SENHA_BANCO);
        } catch (SQLException | IllegalStateException erroConexao) {
            throw new RuntimeException("Erro na conexao com o Banco de Dados: " + erroConexao.getMessage());
        }
    }
}
