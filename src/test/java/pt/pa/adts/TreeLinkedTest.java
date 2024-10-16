package pt.pa.adts;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TreeLinkedTest {

    private TreeLinked<String> tree;
    private Position<String> root;

    @Before
    public void setUp() throws Exception {
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
    }

    @Test
    public void testSize() {
        assertEquals("A árvore deveria conter 11 elementos.", 11, tree.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse("A árvore deveria conter elementos.", tree.isEmpty());
    }

    @Test
    public void testIsExternal() throws InvalidPositionException {
        Position<String> anchovy = tree.children(root).iterator().next(); // "Anchovy"
        assertTrue("Anchovy deveria ser um nó externo.", tree.isExternal(anchovy));
    }

    @Test
    public void testIsRoot() {
        assertTrue("Ecosystem deveria ser a raiz.", tree.isRoot(root));
    }

    @Test
    public void testInsertShouldReturnCorrectPosition() throws InvalidPositionException {
        Position<String> insects = tree.insert(root, "Insects");
        assertEquals("A posição inserida deveria conter 'Insects'.", "Insects", insects.element());
    }

    @Test
    public void testRemoveShouldReturnCorrectPosition() throws InvalidPositionException {
        // Remove o nó "Anchovy" (o primeiro filho da raiz)
        Position<String> anchovyPosition = tree.children(root).iterator().next(); // Obter a posição de "Anchovy"
        String removedElement = tree.remove(anchovyPosition); // Remover "Anchovy"

        // Verifica se o elemento removido é "Anchovy"
        assertEquals("O elemento removido deveria ser 'Anchovy'.", "Anchovy", removedElement);

        // Verifica se o tamanho da árvore foi atualizado corretamente após a remoção
        assertEquals("A árvore deveria conter 10 elementos após a remoção de 'Anchovy'.", 10, tree.size());
    }


    @Test(expected = InvalidPositionException.class)
    public void testInsertThrowsInvalidPositionException() throws InvalidPositionException, BoundaryViolationException {
        tree.insert(null, "InvalidNode");
    }

    @Test
    public void testDegree() throws InvalidPositionException {
        Position<String> tuna = null;

        // Obter o nó "Tuna" diretamente
        for (Position<String> child : tree.children(root)) {
            if (child.element().equals("Tuna")) {
                tuna = child;
                break; // Para de procurar assim que encontramos "Tuna"
            }
        }

        assertNotNull("Deveria encontrar o nó 'Tuna'.", tuna);

        // Obter o grau de Tuna (neste caso, a profundidade)
        int tunaDegree = tree.degree(tuna);
        System.out.println("Grau de 'Tuna': " + tunaDegree); // Para verificação

        // O grau deve ser 1, pois "Tuna" está um nível abaixo da raiz
        assertEquals("O grau de 'Tuna' deveria ser 1.", 1, tunaDegree);
    }



    @Test
    public void testElementsEmptyTree() {
        TreeLinked<String> emptyTree = new TreeLinked<>();
        Iterable<String> elements = emptyTree.elements();
        assertFalse("A árvore vazia deve retornar uma coleção vazia.", elements.iterator().hasNext());
    }

    @Test
    public void testElementsNonEmptyTree() {
        Iterable<String> elements = tree.elements();
        List<String> expectedElements = Arrays.asList(
                "Ecosystem", "Anchovy", "Tuna", "Mackerel",
                "Barracuda", "Sardine", "Shark", "Dolphin",
                "Eagles", "Snakes", "Rabbits"
        );

        List<String> actualElements = new ArrayList<>();
        elements.forEach(actualElements::add);

        assertEquals("Os elementos da árvore devem corresponder ao esperado.", expectedElements, actualElements);
    }

    @Test
    public void testChildrenNoChildren() throws InvalidPositionException {
        Position<String> anchovy = tree.children(root).iterator().next(); // "Anchovy"
        Iterable<Position<String>> children = tree.children(anchovy);
        assertFalse("Um nó sem filhos deve retornar uma coleção vazia.", children.iterator().hasNext());
    }

    @Test
    public void testChildrenWithChildren() throws InvalidPositionException {
        // Obtém os filhos do root
        Iterable<Position<String>> rootChildren = tree.children(root);

        // Busca o nó "Tuna" explicitamente
        Position<String> tuna = null;
        for (Position<String> child : rootChildren) {
            if (child.element().equals("Tuna")) {
                tuna = child;
                break; // Para de procurar assim que encontramos "Tuna"
            }
        }

        // Verifica se "Tuna" foi encontrado
        assertNotNull("Deveria encontrar o nó 'Tuna'.", tuna);

        // Adiciona print para debugar
        System.out.println("Número de filhos da Tuna: " + tree.degree(tuna));

        // Obtém os filhos de Tuna
        Iterable<Position<String>> children = tree.children(tuna);

        // Adiciona print para debugar
        System.out.println("Filhos de 'Tuna': ");
        for (Position<String> child : children) {
            System.out.println(child.element());
        }

        // Lista dos filhos esperados
        List<String> expectedChildren = Arrays.asList("Mackerel", "Barracuda");

        // Cria uma lista para os filhos atuais
        List<String> actualChildren = new ArrayList<>();
        for (Position<String> child : children) {
            actualChildren.add(child.element());
        }

        // Verifica se a lista de filhos corresponde ao esperado
        assertEquals("Os filhos de 'Tuna' devem corresponder ao esperado.", expectedChildren, actualChildren);
    }




    @Test
    public void testChildrenRoot() throws InvalidPositionException {
        Iterable<Position<String>> children = tree.children(root);
        List<String> expectedChildren = Arrays.asList("Anchovy", "Tuna", "Shark", "Eagles");

        List<String> actualChildren = new ArrayList<>();
        for (Position<String> child : children) {
            actualChildren.add(child.element());
        }

        assertEquals("Os filhos da raiz devem corresponder ao esperado.", expectedChildren, actualChildren);
    }
}