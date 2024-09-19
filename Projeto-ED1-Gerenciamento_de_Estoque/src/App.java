import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ListaEstoque estoque = new ListaEstoque();
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
                    cadastrarProduto(sc, estoque);
                    break;
                case 2:
                    removerProduto(sc, estoque);
                    break;
                case 3:
                    mostrarEstoque(sc, estoque);
                    break;
                case 4:
                    System.out.println("Sistema encerrado.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    public static void cadastrarProduto(Scanner sc, ListaEstoque estoque) {
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
    
    public static void removerProduto(Scanner sc, ListaEstoque estoque) {
    	System.out.print("Nome do produto para remover: ");
        String nomeRemover = sc.nextLine();
        estoque.removerProduto(nomeRemover);
    }
    
    public static void mostrarEstoque(Scanner sc, ListaEstoque estoque) {
    	estoque.mostrarEstoque();
    }
}