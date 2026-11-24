import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TelaCrudRemedios extends JDialog {
    private JTextField campoCodigoIdentificador;
    private JTextField campoNomeMedicamento;
    private JTextField campoFabricanteMedicamento;
    private JTextField campoPrecoUnitario;
    private JTextField campoQuantidadeEstoque;
    private JTable tabelaMedicamentos;
    private DefaultTableModel modeloDadosTabela;
    private RemedioDAO repositorioRemedios;

    public TelaCrudRemedios(JFrame janelaPai) {
        super(janelaPai, "Gerenciar Medicamentos", true);
        setSize(650, 500);
        setLocationRelativeTo(janelaPai);
        repositorioRemedios = new RemedioDAO();

        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("ID (Automatico):"));
        campoCodigoIdentificador = new JTextField();
        campoCodigoIdentificador.setEditable(false);
        painelFormulario.add(campoCodigoIdentificador);

        painelFormulario.add(new JLabel("Nome do Remedio:"));
        campoNomeMedicamento = new JTextField();
        painelFormulario.add(campoNomeMedicamento);

        painelFormulario.add(new JLabel("Fabricante:"));
        campoFabricanteMedicamento = new JTextField();
        painelFormulario.add(campoFabricanteMedicamento);

        painelFormulario.add(new JLabel("Preco (R$):"));
        campoPrecoUnitario = new JTextField();
        painelFormulario.add(campoPrecoUnitario);

        painelFormulario.add(new JLabel("Quantidade:"));
        campoQuantidadeEstoque = new JTextField();
        painelFormulario.add(campoQuantidadeEstoque);

        JPanel painelAcoes = new JPanel(new FlowLayout());
        JButton botaoSalvar = new JButton("Cadastrar");
        JButton botaoAtualizar = new JButton("Atualizar");
        JButton botaoExcluir = new JButton("Excluir");
        JButton botaoLimpar = new JButton("Limpar");

        painelAcoes.add(botaoSalvar);
        painelAcoes.add(botaoAtualizar);
        painelAcoes.add(botaoExcluir);
        painelAcoes.add(botaoLimpar);

        modeloDadosTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Fabricante", "Preco", "Qtd"}, 0);
        tabelaMedicamentos = new JTable(modeloDadosTabela);
        JScrollPane barraRolagem = new JScrollPane(tabelaMedicamentos);
        barraRolagem.setPreferredSize(new Dimension(600, 200));

        setLayout(new BorderLayout());
        add(painelFormulario, BorderLayout.NORTH);
        add(painelAcoes, BorderLayout.CENTER);
        add(barraRolagem, BorderLayout.SOUTH);

        botaoSalvar.addActionListener(eventoClique -> cadastrar());
        botaoAtualizar.addActionListener(eventoClique -> atualizar());
        botaoExcluir.addActionListener(eventoClique -> excluir());
        botaoLimpar.addActionListener(eventoClique -> limparCampos());

        tabelaMedicamentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent eventoMouse) {
                preencherFormulario();
            }
        });

        carregarTabela();
    }

    private void cadastrar() {
        try {
            Remedio novoRemedio = new Remedio();
            novoRemedio.setNome(campoNomeMedicamento.getText());
            novoRemedio.setFabricante(campoFabricanteMedicamento.getText());
            novoRemedio.setPreco(Double.parseDouble(campoPrecoUnitario.getText().replace(",", ".")));
            novoRemedio.setQuantidade(Integer.parseInt(campoQuantidadeEstoque.getText()));

            repositorioRemedios.inserir(novoRemedio);
            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception erroCadastro) {
            JOptionPane.showMessageDialog(this, "Erro: Verifique os numeros preenchidos. " + erroCadastro.getMessage());
        }
    }

    private void atualizar() {
        if (campoCodigoIdentificador.getText().isEmpty()) return;
        try {
            Remedio remedioAtualizado = new Remedio();
            remedioAtualizado.setId(Integer.parseInt(campoCodigoIdentificador.getText()));
            remedioAtualizado.setNome(campoNomeMedicamento.getText());
            remedioAtualizado.setFabricante(campoFabricanteMedicamento.getText());
            remedioAtualizado.setPreco(Double.parseDouble(campoPrecoUnitario.getText().replace(",", ".")));
            remedioAtualizado.setQuantidade(Integer.parseInt(campoQuantidadeEstoque.getText()));

            repositorioRemedios.atualizar(remedioAtualizado);
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception erroAtualizacao) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar. " + erroAtualizacao.getMessage());
        }
    }

    private void excluir() {
        if (campoCodigoIdentificador.getText().isEmpty()) return;
        int confirmacaoExclusao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir este medicamento?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
        );
        if (confirmacaoExclusao == JOptionPane.YES_OPTION) {
            repositorioRemedios.excluir(Integer.parseInt(campoCodigoIdentificador.getText()));
            JOptionPane.showMessageDialog(this, "Excluido com sucesso!");
            limparCampos();
            carregarTabela();
        }
    }

    private void carregarTabela() {
        modeloDadosTabela.setRowCount(0);
        List<Remedio> medicamentos = repositorioRemedios.listarTodos();
        for (Remedio medicamentoAtual : medicamentos) {
            modeloDadosTabela.addRow(new Object[]{
                    medicamentoAtual.getId(),
                    medicamentoAtual.getNome(),
                    medicamentoAtual.getFabricante(),
                    medicamentoAtual.getPreco(),
                    medicamentoAtual.getQuantidade()
            });
        }
    }

    private void preencherFormulario() {
        int linhaSelecionada = tabelaMedicamentos.getSelectedRow();
        if (linhaSelecionada != -1) {
            campoCodigoIdentificador.setText(modeloDadosTabela.getValueAt(linhaSelecionada, 0).toString());
            campoNomeMedicamento.setText(modeloDadosTabela.getValueAt(linhaSelecionada, 1).toString());
            campoFabricanteMedicamento.setText(modeloDadosTabela.getValueAt(linhaSelecionada, 2).toString());
            campoPrecoUnitario.setText(modeloDadosTabela.getValueAt(linhaSelecionada, 3).toString());
            campoQuantidadeEstoque.setText(modeloDadosTabela.getValueAt(linhaSelecionada, 4).toString());
        }
    }

    private void limparCampos() {
        campoCodigoIdentificador.setText("");
        campoNomeMedicamento.setText("");
        campoFabricanteMedicamento.setText("");
        campoPrecoUnitario.setText("");
        campoQuantidadeEstoque.setText("");
        tabelaMedicamentos.clearSelection();
    }
}
