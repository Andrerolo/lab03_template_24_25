package pt.pa;

import pt.pa.adts.Position;
import pt.pa.adts.TreeLinked;

/**
 *
 * @author amfs
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TreeLinked<String> tree;
        Position<String> root;

        // Criar a árvore como no método main()
        tree = new TreeLinked<>("Ecosystem");
        root = tree.root();

        // Adicionando os elementos conforme a nova estrutura desejada
        Position<String> anchovy = tree.insert(root, "Anchovy");
        Position<String> tuna = tree.insert(root, "Tuna");

        // Filhos de Tuna
        Position<String> mackerel = tree.insert(tuna, "Mackerel");
        Position<String> barracuda = tree.insert(tuna, "Barracuda");

        // Filho de Barracuda
        tree.insert(barracuda, "Sardine");

        // Filhos de Shark
        Position<String> shark = tree.insert(root, "Shark");
        tree.insert(shark, "Dolphin");

        // Filhos de Eagles
        Position<String> eagles = tree.insert(root, "Eagles");
        Position<String> snakes = tree.insert(eagles, "Snakes");
        tree.insert(eagles, "Rabbits");
        tree.insert(eagles, "teste");

        // Imprimindo a árvore
        System.out.println(tree.toString());
    }


}
