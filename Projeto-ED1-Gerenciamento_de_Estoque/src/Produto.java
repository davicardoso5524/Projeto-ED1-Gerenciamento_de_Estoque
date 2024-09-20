import java.io.Serializable;
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	private static int contadorID = 1;  // Gerador de IDs
	private int id;
	private String nome;
	private int quantidade;
	private double preco;
	public Produto(String nome, double preco, int quantidade) {
		this.id = contadorID++;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	@Override
    public String toString() {
        return String.format("ID: %d | Produto: %s | Quantidade: %d | Preço: R$ %.2f",
                id, nome, quantidade, preco);
    }	
}
