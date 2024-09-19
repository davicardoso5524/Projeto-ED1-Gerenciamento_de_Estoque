public class No {
	Produto produto;
	No anterior;
	No proximo;
	
	public No(Produto produto) {
		this.produto = produto;
		this.anterior = this;
		this.proximo = this;
	}
}
