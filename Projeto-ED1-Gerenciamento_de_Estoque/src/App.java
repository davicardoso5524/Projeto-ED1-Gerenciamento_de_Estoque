import java.io.*;
import java.util.Scanner;

public class App {
	
	private static final String ARQUIVO_ESTOQUE = "estoque.ser";
    private static ListaEstoque estoque = new ListaEstoque();
	
    public static void main(String[] args) {
    	carregarEstoque();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- Sistema de Gerenciamento de Estoque -----");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Remover produto");
            System.out.println("3. Mostrar estoque");
            System.out.println("4. Sair");
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
    	System.out.print("Nome do produto para remover: ");
        String nomeRemover = sc.nextLine();
        estoque.removerProduto(nomeRemover);
    }
    
    public static void mostrarEstoque(Scanner sc) {
    	estoque.mostrarEstoque();
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
}