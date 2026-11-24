public class Usuario {
    private int codigoUsuario;
    private String loginUsuario;
    private String senhaUsuario;

    public Usuario() {}

    public Usuario(String login, String senha) {
        this.loginUsuario = login;
        this.senhaUsuario = senha;
    }

    public int getId() { return codigoUsuario; }
    public void setId(int id) { this.codigoUsuario = id; }

    public String getLogin() { return loginUsuario; }
    public void setLogin(String login) { this.loginUsuario = login; }

    public String getSenha() { return senhaUsuario; }
    public void setSenha(String senha) { this.senhaUsuario = senha; }
}
