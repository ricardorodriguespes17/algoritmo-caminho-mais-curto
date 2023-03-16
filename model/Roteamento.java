package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Roteamento {
  private static final int INF = Integer.MAX_VALUE;

  public static List<Integer> caminhoMinimo(int[][] grafo, int origem, int objetivo) {
    int numeroVertices = grafo.length;
    int[] distancia = new int[numeroVertices];
    Arrays.fill(distancia, INF);
    distancia[origem] = 0;

    int[] prodecessor = new int[numeroVertices];
    Arrays.fill(prodecessor, -1);

    PriorityQueue<Integer> filaDePrioridade = new PriorityQueue<>(Comparator.comparingInt(v -> distancia[v]));
    filaDePrioridade.offer(origem);

    while (!filaDePrioridade.isEmpty()) {
      int verticeMenorDistancia = filaDePrioridade.poll();

      for (int vertice = 0; vertice < numeroVertices; vertice++) {
        if (grafo[verticeMenorDistancia][vertice] == INF) {
          continue;
        }

        int newDistancia = distancia[verticeMenorDistancia] + (int) Math.sqrt(grafo[verticeMenorDistancia][vertice]);
        if (newDistancia < distancia[vertice]) {
          filaDePrioridade.remove(vertice);
          distancia[vertice] = newDistancia;
          prodecessor[vertice] = verticeMenorDistancia;
          filaDePrioridade.offer(vertice);
        }
      }
    }

    List<Integer> caminho = new ArrayList<>();
    int vertice = objetivo;
    while (prodecessor[vertice] != -1) {
      caminho.add(0, vertice);
      vertice = prodecessor[vertice];
    }
    caminho.add(0, vertice);

    return caminho;
  }
}
