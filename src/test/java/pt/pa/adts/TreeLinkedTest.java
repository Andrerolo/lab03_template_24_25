package pt.pa.adts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeLinkedTest {

    private TreeLinked<String> tree;
    private Position<String> root;
    private Position<String> animals;
    private Position<String> plants;

    @Before
    public void setUp() throws Exception {
        // Criar a árvore como no método main()
        tree = new TreeLinked<>("Ecosystem");
        root = tree.root();

        // Nível 1
        animals = tree.insert(root, "Animals");
        plants = tree.insert(root, "Plants");

        // Nível 2
        Position<String> mammals = tree.insert(animals, "Mammals");
        Position<String> birds = tree.insert(animals, "Birds");
        Position<String> trees = tree.insert(plants, "Trees");
        Position<String> flowers = tree.insert(plants, "Flowers");

        // Nível 3
        tree.insert(mammals, "Humans");
        tree.insert(mammals, "Whales");
        tree.insert(birds, "Eagles");
        tree.insert(birds, "Parrots");
        tree.insert(trees, "Oak");
        tree.insert(trees, "Pine");
        tree.insert(flowers, "Rose");
        tree.insert(flowers, "Tulip");
    }

    @Test
    public void size() {
    }

    @Test
    public void testSize() {
        assertEquals("A árvore deveria conter 15 elementos.", 15, tree.size());
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void replace() {
    }

    @Test
    public void root() {
    }

    @Test
    public void parent() {
    }

    @Test
    public void children() {
    }

    @Test
    public void isInternal() {
    }

    @Test
    public void isExternal() {
    }

    @Test
    public void isRoot() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void positions() {
    }

    @Test
    public void elements() {
    }

    @Test
    public void height() {
    }

    @Test
    public void move() {
    }

    @Test
    public void isAncestor() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testIsEmpty() {
        // A árvore não está vazia, pois foi inicializada com elementos
        assertFalse("A árvore deveria conter elementos.", tree.isEmpty());
    }

    @Test
    public void testIsExternal() throws InvalidPositionException {
        // Verificar se um nó sem filhos é externo (ex: "Humans")
        Position<String> mammals = tree.children(animals).iterator().next(); // "Mammals"
        Position<String> humans = tree.children(mammals).iterator().next();  // "Humans"
        assertTrue("Humans deveria ser um nó externo.", tree.isExternal(humans));

        // Verificar se "Animals" não é externo, pois tem filhos
        assertFalse("Animals não deveria ser um nó externo.", tree.isExternal(animals));
    }

    @Test
    public void testIsRoot() throws InvalidPositionException {
        // Verificar se o nó "Ecosystem" é a raiz
        assertTrue("Ecosystem deveria ser a raiz.", tree.isRoot(root));

        // Verificar se "Animals" não é a raiz
        assertFalse("Animals não deveria ser a raiz.", tree.isRoot(animals));
    }


}