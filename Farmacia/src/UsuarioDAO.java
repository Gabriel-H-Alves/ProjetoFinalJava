import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean autenticar(String loginInformado, String senhaInformada) {
        String comandoSql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        try (Connection conexaoBanco = ConexaoDB.getConexao();
             PreparedStatement declaracaoSql = conexaoBanco.prepareStatement(comandoSql)) {

            declaracaoSql.setString(1, loginInformado);
            declaracaoSql.setString(2, senhaInformada);
            ResultSet resultadoConsulta = declaracaoSql.executeQuery();

            return resultadoConsulta.next();

        } catch (SQLException erroAutenticacao) {
            throw new RuntimeException("Erro ao autenticar: " + erroAutenticacao.getMessage());
        }
    }

    public void inserir(Usuario novoUsuario) {
        String comandoSql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";
        try (Connection conexaoBanco = ConexaoDB.getConexao();
             PreparedStatement declaracaoSql = conexaoBanco.prepareStatement(comandoSql)) {

            declaracaoSql.setString(1, novoUsuario.getLogin());
            declaracaoSql.setString(2, novoUsuario.getSenha());
            declaracaoSql.execute();

        } catch (SQLException erroCadastro) {
            throw new RuntimeException("Erro ao cadastrar usuario: " + erroCadastro.getMessage());
        }
    }
}
