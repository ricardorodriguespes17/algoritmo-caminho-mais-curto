import java.util.List;

public class Principal {
  public static void main(String args[]) {
    int I = Integer.MAX_VALUE;

    int[][] grafo = {
        // 0 1, 2, 3, 4, 5
        { 0, 8, 5, 2, I, I }, // 0
        { 8, 0, 2, I, I, 4 }, // 1
        { 5, 2, 0, I, 4, I }, // 2
        { 2, I, I, 0, 5, I }, // 3
        { I, I, 4, 5, 0, 2 }, // 4
        { I, 4, I, I, 2, 0 }, // 5
    };

    List<Integer> resultado = Roteamento.caminhoMinimo(grafo, 0, 5);

    for (int vertice : resultado) {
      System.out.println(vertice);
    }
  }
}
