public class Remedio {
    private int codigoIdentificador;
    private String nomeMedicamento;
    private String fabricanteMedicamento;
    private double precoUnitario;
    private int quantidadeEstoque;

    public Remedio() {}

    public int getId() { return codigoIdentificador; }
    public void setId(int id) { this.codigoIdentificador = id; }

    public String getNome() { return nomeMedicamento; }
    public void setNome(String nome) { this.nomeMedicamento = nome; }

    public String getFabricante() { return fabricanteMedicamento; }
    public void setFabricante(String fabricante) { this.fabricanteMedicamento = fabricante; }

    public double getPreco() { return precoUnitario; }
    public void setPreco(double preco) { this.precoUnitario = preco; }

    public int getQuantidade() { return quantidadeEstoque; }
    public void setQuantidade(int quantidade) { this.quantidadeEstoque = quantidade; }
}
