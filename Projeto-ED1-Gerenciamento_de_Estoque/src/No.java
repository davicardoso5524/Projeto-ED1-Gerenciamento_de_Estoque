import java.io.Serializable;
public class No implements Serializable{
	private static final long serialVersionUID = 1L;
	Produto produto;
	No anterior;
	No proximo;
	
	public No(Produto produto) {
		this.produto = produto;
		this.anterior = this;
		this.proximo = this;
	}
}
