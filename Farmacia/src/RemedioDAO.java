import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RemedioDAO {

    public void inserir(Remedio medicamentoParaInserir) {
        String comandoSql = "INSERT INTO remedios (nome, fabricante, preco, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection conexaoBanco = ConexaoDB.getConexao();
             PreparedStatement declaracaoSql = conexaoBanco.prepareStatement(comandoSql)) {
            declaracaoSql.setString(1, medicamentoParaInserir.getNome());
            declaracaoSql.setString(2, medicamentoParaInserir.getFabricante());
            declaracaoSql.setDouble(3, medicamentoParaInserir.getPreco());
            declaracaoSql.setInt(4, medicamentoParaInserir.getQuantidade());
            declaracaoSql.execute();
        } catch (SQLException erroInserir) {
            throw new RuntimeException("Erro ao inserir: " + erroInserir.getMessage());
        }
    }

    public List<Remedio> listarTodos() {
        String comandoSql = "SELECT * FROM remedios";
        List<Remedio> listaMedicamentos = new ArrayList<>();
        try (Connection conexaoBanco = ConexaoDB.getConexao();
             PreparedStatement declaracaoSql = conexaoBanco.prepareStatement(comandoSql);
             ResultSet resultadoConsulta = declaracaoSql.executeQuery()) {
            while (resultadoConsulta.next()) {
                Remedio medicamentoEncontrado = new Remedio();
                medicamentoEncontrado.setId(resultadoConsulta.getInt("id"));
                medicamentoEncontrado.setNome(resultadoConsulta.getString("nome"));
                medicamentoEncontrado.setFabricante(resultadoConsulta.getString("fabricante"));
                medicamentoEncontrado.setPreco(resultadoConsulta.getDouble("preco"));
                medicamentoEncontrado.setQuantidade(resultadoConsulta.getInt("quantidade"));
                listaMedicamentos.add(medicamentoEncontrado);
            }
        } catch (SQLException erroListagem) {
            throw new RuntimeException("Erro ao listar: " + erroListagem.getMessage());
        }
        return listaMedicamentos;
    }

    public void atualizar(Remedio medicamentoParaAtualizar) {
        String comandoSql = "UPDATE remedios SET nome=?, fabricante=?, preco=?, quantidade=? WHERE id=?";
        try (Connection conexaoBanco = ConexaoDB.getConexao();
             PreparedStatement declaracaoSql = conexaoBanco.prepareStatement(comandoSql)) {
            declaracaoSql.setString(1, medicamentoParaAtualizar.getNome());
            declaracaoSql.setString(2, medicamentoParaAtualizar.getFabricante());
            declaracaoSql.setDouble(3, medicamentoParaAtualizar.getPreco());
            declaracaoSql.setInt(4, medicamentoParaAtualizar.getQuantidade());
            declaracaoSql.setInt(5, medicamentoParaAtualizar.getId());
            declaracaoSql.execute();
        } catch (SQLException erroAtualizacao) {
            throw new RuntimeException("Erro ao atualizar: " + erroAtualizacao.getMessage());
        }
    }

    public void excluir(int codigoMedicamento) {
        String comandoSql = "DELETE FROM remedios WHERE id=?";
        try (Connection conexaoBanco = ConexaoDB.getConexao();
             PreparedStatement declaracaoSql = conexaoBanco.prepareStatement(comandoSql)) {
            declaracaoSql.setInt(1, codigoMedicamento);
            declaracaoSql.execute();
        } catch (SQLException erroExclusao) {
            throw new RuntimeException("Erro ao excluir: " + erroExclusao.getMessage());
        }
    }
}
