import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {
    public TelaCadastro() {
        setTitle("Novo Usuario");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JPanel painelNome = new JPanel();
        painelNome.add(new JLabel("Novo Usuario:"));
        JTextField campoNovoUsuario = new JTextField(15);
        painelNome.add(campoNovoUsuario);

        JPanel painelSenha = new JPanel();
        painelSenha.add(new JLabel("Nova Senha:  "));
        JPasswordField campoNovaSenha = new JPasswordField(15);
        painelSenha.add(campoNovaSenha);

        JPanel painelBotoes = new JPanel();
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoVoltar = new JButton("Voltar ao Login");
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoVoltar);

        add(painelNome);
        add(painelSenha);
        add(painelBotoes);

        botaoCadastrar.addActionListener(eventoClique -> {
            String loginDigitado = campoNovoUsuario.getText();
            String senhaDigitada = new String(campoNovaSenha.getPassword());

            if (!loginDigitado.isEmpty() && !senhaDigitada.isEmpty()) {
                try {
                    Usuario novoUsuario = new Usuario(loginDigitado, senhaDigitada);
                    new UsuarioDAO().inserir(novoUsuario);
                    JOptionPane.showMessageDialog(this, "Usuario cadastrado com sucesso!");
                    new TelaLogin().setVisible(true);
                    this.dispose();
                } catch (Exception erroCadastro) {
                    JOptionPane.showMessageDialog(this, "Erro: " + erroCadastro.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            }
        });

        botaoVoltar.addActionListener(eventoClique -> {
            new TelaLogin().setVisible(true);
            this.dispose();
        });
    }
}
