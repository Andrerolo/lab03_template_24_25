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

    @Test
    public void testInsertShouldReturnCorrectPosition() throws InvalidPositionException, BoundaryViolationException {
        // Insere um novo nó "Insects" como filho de "Animals"
        Position<String> insects = tree.insert(animals, "Insects");

        // Verifica se o elemento retornado na posição correta é "Insects"
        assertEquals("A posição inserida deveria conter 'Insects'.", "Insects", insects.element());

        // Verifica se o nó pai de "Insects" é "Animals"
        assertEquals("O pai de 'Insects' deveria ser 'Animals'.", "Animals", tree.parent(insects).element());
    }

    @Test
    public void testRemoveShouldReturnCorrectPosition() throws InvalidPositionException {
        // Remove o nó "Flowers"
        String removedElement = tree.remove(plants); // Altera para remover o nó "Plants"

        // Verifica se o elemento removido é "Plants"
        assertEquals("O elemento removido deveria ser 'Plants'.", "Plants", removedElement);


        // Verifica se o tamanho da árvore foi atualizado corretamente após a remoção
        assertEquals("A árvore deveria conter 8 elementos após a remoção de 'Plants'.", 8, tree.size());
    }


    @Test(expected = InvalidPositionException.class)
    public void testInsertThrowsInvalidPositionException() throws InvalidPositionException, BoundaryViolationException {
        // Tenta inserir um nó com o pai sendo nulo (árvore já contém elementos)
        tree.insert(null, "InvalidNode");

        // O teste falhará se a exceção não for lançada
    }

    @Test
    public void testDegree() throws InvalidPositionException {
        // Verificar o grau do nó "Animals"
        assertEquals("O grau de 'Animals' deveria ser 2.", 2, tree.degree(animals));

        // Verificar o grau do nó "Mammals"
        Position<String> mammals = tree.children(animals).iterator().next(); // "Mammals"
        assertEquals("O grau de 'Mammals' deveria ser 2.", 2, tree.degree(mammals));

        // Verificar o grau do nó "Birds"
        Position<String> birds = tree.children(animals).iterator().next(); // "Birds"
        assertEquals("O grau de 'Birds' deveria ser 2.", 2, tree.degree(birds));

        // Verificar o grau do nó "Humans"
        Position<String> humans = tree.children(mammals).iterator().next(); // "Humans"
        assertEquals("O grau de 'Humans' deveria ser 0.", 0, tree.degree(humans));

        // Verificar o grau do nó "Plants"
        assertEquals("O grau de 'Plants' deveria ser 2.", 2, tree.degree(plants));

        // Verificar o grau do nó "Trees"
        Position<String> trees = tree.children(plants).iterator().next(); // "Trees"
        assertEquals("O grau de 'Trees' deveria ser 2.", 2, tree.degree(trees));

        // Verificar o grau do nó "Flowers"
        Position<String> flowers = tree.children(plants).iterator().next(); // "Flowers"
        assertEquals("O grau de 'Flowers' deveria ser 2.", 2, tree.degree(flowers));
    }


}