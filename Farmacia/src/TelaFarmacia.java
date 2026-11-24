import javax.swing.*;

public class TelaFarmacia extends JFrame {
    public TelaFarmacia() {
        setTitle("Sistema Farmacia - Gestao");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar barraMenuPrincipal = new JMenuBar();

        JMenu menuMedicamentos = new JMenu("Medicamentos");
        JMenuItem itemGerenciarMedicamentos = new JMenuItem("Gerenciar (Cadastrar/Consultar/Editar/Excluir)");
        menuMedicamentos.add(itemGerenciarMedicamentos);

        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem itemEncerrarSessao = new JMenuItem("Sair (Logout)");
        menuSistema.add(itemEncerrarSessao);

        barraMenuPrincipal.add(menuMedicamentos);
        barraMenuPrincipal.add(menuSistema);
        setJMenuBar(barraMenuPrincipal);

        itemGerenciarMedicamentos.addActionListener(eventoClique -> {
            new TelaCrudRemedios(this).setVisible(true);
        });

        itemEncerrarSessao.addActionListener(eventoClique -> {
            int confirmacaoSaida = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente sair?",
                    "Sair",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacaoSaida == JOptionPane.YES_OPTION) {
                new TelaLogin().setVisible(true);
                this.dispose();
            }
        });
    }
}
