import java.io.Serializable;
import java.util.*;
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
	
	public No getPrimeiro() {
		return primeiro;
	}

	public No getUltimo() {
		return ultimo;
	}

	public int getTamanho() {
		return tamanho;
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
	
	// Buscar produto por ID
	public Produto buscarProdutoPorID(int id) {
	    if (tamanho == 0) {
	        System.out.println("Estoque vazio.");
	        return null;
	    }

	    No atual = primeiro;
	    
	    do {
	        if (atual.produto.getId() == id) {
	            System.out.println("Produto encontrado: " + atual.produto);
	            return atual.produto; // Produto encontrado
	        }
	        atual = atual.proximo;
	    } while (atual != primeiro); // Percorrer até voltar ao primeiro

	    System.out.println("Produto com ID " + id + " não encontrado.");
	    return null; // Produto não encontrado
	}
    
 
    public ArrayList<Produto> obterProdutosOrdenadosPorNome() {
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        if (tamanho == 0) return listaProdutos;

        No atual = primeiro;
        do {
            listaProdutos.add(atual.produto);
            atual = atual.proximo;
        } while (atual != primeiro);

        // Ordenando a lista pelo nome do produto
        Collections.sort(listaProdutos, Comparator.comparing(Produto::getNome));
        return listaProdutos;
    }

	
	/*public void removerProduto(String nomeProduto) {
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
	*/
    
 // Método principal para remover um produto
    public void removerProdutoPorNome(String nomeProduto) {
        if (tamanho == 0) {
            System.out.println("Estoque vazio");
            return;
        }

        No atual = primeiro;
        boolean produtoEncontrado = false;

        do {
            if (atual.produto.getNome().equalsIgnoreCase(nomeProduto)) {
                produtoEncontrado = true;
                
                // Verifica se deve apenas decrementar ou remover o nó
                if (atual.produto.getQuantidade() > 1) {
                    decrementarQuantidadeProduto(atual);
                } else {
                    removerNo(atual);
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

    // Método para decrementar a quantidade de um produto
    private void decrementarQuantidadeProduto(No atual) {
        atual.produto.setQuantidade(atual.produto.getQuantidade() - 1);
        System.out.println("Uma unidade do produto " + atual.produto.getNome() 
        + " foi removida. Quantidade restante: " + atual.produto.getQuantidade());
    }

    // Método para remover completamente o nó da lista
    private void removerNo(No atual) {
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
    }
    
    public void removerTodosProdutos() {
        if (tamanho == 0) {
            System.out.println("Estoque já está vazio.");
            return;
        }
        
        primeiro = null;
        ultimo = null;
        tamanho = 0;
        System.out.println("Todos os produtos foram removidos do estoque.");
    }
    
	public void mostrarEstoque() {
		if (tamanho == 0) {
			System.out.println("Estoque vazio");
			return;
		}
		
		No atual = primeiro;
		System.out.println("--- Estoque ---");	
		do {
			System.out.println(atual.produto);
			atual = atual.proximo;
		} while (atual != primeiro);
	}
}
