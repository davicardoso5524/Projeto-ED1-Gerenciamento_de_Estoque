import java.io.Serializable;
public class ListaEstoque implements Serializable{
	private static final long serialVersionUID = 1L;
	private No primeiro;
	private No ultimo;
	private int tamanho; // contador para saber a quantidade de produtos
	
	public ListaEstoque() {
		this.primeiro = null;
		this.ultimo = null;
		this.tamanho = 0;
	}
	
	public void adicionarProduto(Produto produto) {
		No novoNo = new No(produto);
		if (tamanho == 0) {
			primeiro = novoNo;
			ultimo = novoNo;
		} else {
			novoNo.proximo = primeiro;
			novoNo.anterior = ultimo;
			ultimo.proximo = novoNo;
			primeiro.anterior = novoNo;
			ultimo = novoNo;
		}
		tamanho++;
	}
	
	/*
	public void remover(String nome) {
		No atual = primeiro;
		if (tamanho == 0) {
			return;
		}
		do {
			if (atual.produto.getNome().equals(nome)) {
				if (tamanho == 1) {
					primeiro = null;
					ultimo = null;
				} else {
					atual.anterior.proximo = atual.proximo;
					atual.proximo.anterior = atual.anterior;
					if (atual == primeiro) {
						primeiro = atual.proximo;
					}
					if (atual == ultimo) {
						ultimo = atual.anterior;
					}
				}
				tamanho--;
				return;
			}
		}while(atual != primeiro);
	}
	*/
	public void removerProduto(String nomeProduto) {
	    if (tamanho == 0) {
	        System.out.println("Estoque vazio");
	        return;
	    }

	    No atual = primeiro;
	    boolean produtoEncontrado = false;

	    do {
	        if (atual.produto.getNome().equalsIgnoreCase(nomeProduto)) {
	            produtoEncontrado = true;
	            // Verifica se o produto tem mais de uma unidade
	            if (atual.produto.getQuantidade() > 1) {
	                // Decrementa a quantidade
	                atual.produto.setQuantidade(atual.produto.getQuantidade() - 1);
	                System.out.println("Uma unidade do produto " + nomeProduto + " foi removida. Quantidade restante: " + atual.produto.getQuantidade());
	            } else {
	                // Se a quantidade for 1, remove o nó
	                if (atual == primeiro && atual == ultimo) { 
	                    // Caso a lista tenha apenas um nó
	                    primeiro = null;
	                    ultimo = null;
	                } else if (atual == primeiro) {
	                    // Se for o primeiro nó
	                    primeiro = atual.proximo;
	                    primeiro.anterior = ultimo;
	                    ultimo.proximo = primeiro;
	                } else if (atual == ultimo) {
	                    // Se for o último nó
	                    ultimo = atual.anterior;
	                    ultimo.proximo = primeiro;
	                    primeiro.anterior = ultimo;
	                } else {
	                    // Caso comum, removendo um nó no meio
	                    atual.anterior.proximo = atual.proximo;
	                    atual.proximo.anterior = atual.anterior;
	                }
	                tamanho--;
	                System.out.println("O produto " + nomeProduto + " foi removido do estoque.");
	            }
	            break;
	        }
	        atual = atual.proximo;
	    } while (atual != primeiro);

	    if (!produtoEncontrado) {
	        System.out.println("Produto " + nomeProduto + " não encontrado no estoque.");
	    }
	}
	
	public void mostrarEstoque() {
		if (tamanho == 0) {
			System.out.println("Estoque vazio");
			return;
		}
		
		No atual = primeiro;
		
		do {
			System.out.println(atual.produto);
			atual = atual.proximo;
		} while (atual != primeiro);
	}
}
