import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        setTitle("Sistema Farmacia - Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JPanel painelUsuario = new JPanel(new FlowLayout());
        painelUsuario.add(new JLabel("Usuario:"));
        JTextField campoUsuario = new JTextField(15);
        painelUsuario.add(campoUsuario);

        JPanel painelSenha = new JPanel(new FlowLayout());
        painelSenha.add(new JLabel("Senha:  "));
        JPasswordField campoSenha = new JPasswordField(15);
        painelSenha.add(campoSenha);

        JPanel painelBotaoLogin = new JPanel();
        JButton botaoEntrar = new JButton("Entrar");
        painelBotaoLogin.add(botaoEntrar);

        JPanel painelLinks = new JPanel(new FlowLayout());
        JLabel linkEsqueciSenha = new JLabel("<html><u>Esqueci minha senha</u></html>");
        linkEsqueciSenha.setForeground(Color.BLUE);
        linkEsqueciSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel linkCadastrar = new JLabel("<html><u>Cadastre-se</u></html>");
        linkCadastrar.setForeground(Color.BLUE);
        linkCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        painelLinks.add(linkEsqueciSenha);
        painelLinks.add(new JLabel(" | "));
        painelLinks.add(linkCadastrar);

        add(painelUsuario);
        add(painelSenha);
        add(painelBotaoLogin);
        add(painelLinks);

        botaoEntrar.addActionListener(eventoClique -> {
            String loginDigitado = campoUsuario.getText();
            String senhaDigitada = new String(campoSenha.getPassword());

            try {
                UsuarioDAO repositorioUsuario = new UsuarioDAO();
                if (repositorioUsuario.autenticar(loginDigitado, senhaDigitada)) {
                    new TelaFarmacia().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario ou senha incorretos!");
                }
            } catch (RuntimeException erroAcessoBanco) {
                JOptionPane.showMessageDialog(this, erroAcessoBanco.getMessage(),
                        "Erro ao acessar o banco", JOptionPane.ERROR_MESSAGE);
            }
        });

        linkCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent eventoMouse) {
                new TelaCadastro().setVisible(true);
                dispose();
            }
        });

        linkEsqueciSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent eventoMouse) {
                JOptionPane.showMessageDialog(null, "Contate o administrador para redefinir a senha.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
