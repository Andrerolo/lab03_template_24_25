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
        /*
        * Ecosystem
              -Animals
                -Mammals
                  -Humans
                  -Whales
                -Birds
                  -Eagles
                  -Parrots
              -Plants
                -Trees
                  -Oak
                  -Pine
                -Flowers
                  -Rose
                  -Tulip

        * */

        // Criação da instância TreeLinked com a raiz "Ecosystem"
        TreeLinked<String> tree = new TreeLinked<>("Ecosystem");

        // Adicionando nós à árvore de acordo com a Figura 1
        Position<String> root = tree.root();

        // Primeiro nível de filhos de "Ecosystem"
        Position<String> animals = tree.insert(root, "Animals");
        Position<String> plants = tree.insert(root, "Plants");

        // Segundo nível - filhos de "Animals"
        Position<String> mammals = tree.insert(animals, "Mammals");
        Position<String> birds = tree.insert(animals, "Birds");

        // Segundo nível - filhos de "Plants"
        Position<String> trees = tree.insert(plants, "Trees");
        Position<String> flowers = tree.insert(plants, "Flowers");

        // Terceiro nível - filhos de "Mammals"
        tree.insert(mammals, "Humans");
        tree.insert(mammals, "Whales");

        // Terceiro nível - filhos de "Birds"
        tree.insert(birds, "Eagles");
        tree.insert(birds, "Parrots");

        // Terceiro nível - filhos de "Trees"
        tree.insert(trees, "Oak");
        tree.insert(trees, "Pine");

        // Terceiro nível - filhos de "Flowers"
        tree.insert(flowers, "Rose");
        tree.insert(flowers, "Tulip");

        // Imprimindo a árvore
        System.out.println(tree.toString());
    }


}
