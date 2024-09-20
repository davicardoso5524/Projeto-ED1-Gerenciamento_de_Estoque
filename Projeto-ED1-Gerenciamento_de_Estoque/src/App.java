import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
public class App {
	private static final String ARQUIVO_ESTOQUE = "estoque.ser";
    private static ListaEstoque estoque = new ListaEstoque();
    public static void main(String[] args) {
    	carregarEstoque();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n----- Sistema de Gerenciamento de Estoque -----");
            System.out.println("[ 1 ] - Adicionar Produto");
            System.out.println("[ 2 ] - Remover Produto");
            System.out.println("[ 3 ] - Mostrar Estoque");
            System.out.println("[ 4 ] - Importar Estoque");
            System.out.println("[ 5 ] - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Consumir a nova linha
            switch (opcao) {
                case 1:
                    cadastrarProduto(sc);
                    break;
                case 2:
                    removerProduto(sc);
                    break;
                case 3:
                    mostrarEstoque(sc);
                    break;
                case 4:
                	importarProdutosDeArquivoExterno();
                case 5:
                	salvarEstoque();
                    System.out.println("Sistema encerrado.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    public static void cadastrarProduto(Scanner sc) {
    	System.out.print("Informe o Nome do produto: ");
        String nomeProduto = sc.nextLine();
        System.out.print("Informe o preço: ");
        double precoProduto = sc.nextDouble();
        System.out.print("Informe qual a quantidade do Produto: ");
        int quantProduto = sc.nextInt();
        if (quantProduto > 0) {
            estoque.adicionarProduto(new Produto(nomeProduto, precoProduto, quantProduto));
            System.out.println("Produto adicionado com sucesso!");
        } else {
            do {
                System.out.println("É necessário cadastrar pelo menos 1 produto.");
                System.out.println("Informe a quantidade de Produtos: ");
                quantProduto = sc.nextInt();
            } while (quantProduto <= 0); // Garante que o valor inserido seja maior que 0
            
            // Depois que o valor válido é inserido, adicionar o produto
            estoque.adicionarProduto(new Produto(nomeProduto, precoProduto, quantProduto));
            System.out.println("Produto adicionado com sucesso!");
        }
    }
    
    public static void removerProduto(Scanner sc) {
    	System.out.println("Escolha uma opção:");
        System.out.println("1 - Remover um produto");
        System.out.println("2 - Remover todos os produtos");
        int opcao = sc.nextInt();
        sc.nextLine();
        
        if (opcao == 1) {
        	estoque.mostrarEstoque();
        	System.out.println();
            System.out.print("Digite o nome do produto a ser removido: ");
            String nomeProduto = sc.nextLine();
            
            estoque.removerProdutoPorNome(nomeProduto);
        } else if (opcao == 2) {
        	estoque.removerTodosProdutos();
        } else {
            System.out.println("Opção inválida.");
        }
    }    
    public static void mostrarEstoque(Scanner sc) {
    	System.out.println("\nOpções de visualização do estoque:");
        System.out.println("[ 1 ] - Buscar produto por ID");
        System.out.println("[ 2 ] - Mostrar estoque ordenado por nome");
        System.out.println("[ 3 ] - Mostrar todo o estoque");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine(); // Limpa o buffer do scanner

        switch (opcao) {
            case 1:
                System.out.print("Digite o ID do produto: ");
                int id = sc.nextInt();
                estoque.buscarProdutoPorID(id);
                break;
            case 2:
            	// Mostrar produtos em ordem alfabética
                ArrayList<Produto> produtosOrdenados = estoque.obterProdutosOrdenadosPorNome();
                if (produtosOrdenados.isEmpty()) {
                    System.out.println("Estoque vazio.");
                } else {
                    System.out.println("Produtos em ordem alfabética:");
                    for (Produto p : produtosOrdenados) {
                        System.out.println(p);
                    }
                }
                break;
            case 3:
            	System.out.println("Todo o estoque:");
                estoque.mostrarEstoque();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }    	
    }
    
 // Método para salvar o estoque em arquivo
    private static void salvarEstoque() {
        try (FileOutputStream fileOut = new FileOutputStream(ARQUIVO_ESTOQUE);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(estoque);
            System.out.println("Estoque salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para carregar o estoque de arquivo
    private static void carregarEstoque() {
        try (FileInputStream fileIn = new FileInputStream(ARQUIVO_ESTOQUE);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            estoque = (ListaEstoque) in.readObject();
            System.out.println("Estoque carregado com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo de estoque encontrado. Um novo será criado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void importarProdutosDeArquivoExterno() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione um arquivo de produtos");

        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();

            try (FileInputStream fileIn = new FileInputStream(arquivoSelecionado);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

                
                ListaEstoque listaImportada = (ListaEstoque) in.readObject();
                No atual = listaImportada.getPrimeiro(); // Obtém o primeiro nó

                
                do {
                    estoque.adicionarProduto(atual.produto);
                    atual = atual.proximo;
                } while (atual != listaImportada.getPrimeiro()); 

                System.out.println("Produtos importados com sucesso do arquivo: " 
                + arquivoSelecionado.getName());

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + arquivoSelecionado.getName());
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Classe não encontrada: " + e.getMessage());
            } catch (ClassCastException e) {
                System.out.println("Erro de tipo ao importar produtos. Verifique o arquivo.");
            }
        } else {
            System.out.println("A importação foi cancelada.");
        }
    }

}
