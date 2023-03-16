package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import model.Roteamento;

public class TelaController implements Initializable {

  @FXML
  private Line aresta01, aresta02, aresta14, aresta13, aresta23, aresta34, aresta35, aresta46, aresta56, aresta25;
  @FXML
  private VBox vertice1, vertice2, vertice3, vertice4, vertice5, vertice6, vertice0;
  @FXML
  private TextField textFieldOrigem, textFieldDestino;

  private List<VBox> vertices = new ArrayList<>();

  private int I = Integer.MAX_VALUE;

  private int pesoAresta01, pesoAresta02, pesoAresta14, pesoAresta13, pesoAresta23, pesoAresta34,
      pesoAresta35, pesoAresta46, pesoAresta56, pesoAresta25;

  @FXML
  public void enviarDados() {
    String origem = textFieldOrigem.getText();
    String destino = textFieldDestino.getText();

    if (origem.equals("") || destino.equals(""))
      return;

    int numOrigem = Integer.parseInt(origem);
    int numDestino = Integer.parseInt(destino);

    List<Integer> resultado = Roteamento.caminhoMinimo(montarGrafo(), numOrigem, numDestino);

    if (resultado.get(0) != numOrigem) {
      System.out.println("Não é possível chegar");
    } else {
      limparVertices();

      Platform.runLater(() -> {
        for (int vertice : resultado) {
          int index = resultado.indexOf(vertice);

          vertices.get(vertice).getStyleClass().add("ativado");

          System.out.print(vertice);
          if (index != resultado.size() - 1)
            System.out.print(" -> ");
          else
            System.out.println();

          // try {
          // Thread.sleep(500);
          // } catch (InterruptedException e) {
          // e.printStackTrace();
          // }
        }
      });
    }

  }

  @FXML
  public void ativarAresta(MouseEvent event) {
    Line arestaSelecionada = (Line) event.getSource();
    boolean ativa = arestaSelecionada.getStyleClass().size() > 0;
    int pesoAresta = I;

    if (ativa) {
      arestaSelecionada.getStyleClass().remove("arestas");
    } else {
      pesoAresta = 1;
      arestaSelecionada.getStyleClass().add("arestas");
    }

    switch (arestaSelecionada.getId()) {
      case "aresta01":
        pesoAresta01 = pesoAresta;
        break;
      case "aresta02":
        pesoAresta02 = pesoAresta;
        break;
      case "aresta14":
        pesoAresta14 = pesoAresta;
        break;
      case "aresta13":
        pesoAresta13 = pesoAresta;
        break;
      case "aresta23":
        pesoAresta23 = pesoAresta;
        break;
      case "aresta34":
        pesoAresta34 = pesoAresta;
        break;
      case "aresta35":
        pesoAresta35 = pesoAresta;
        break;
      case "aresta46":
        pesoAresta46 = pesoAresta;
        break;
      case "aresta56":
        pesoAresta56 = pesoAresta;
        break;
      case "aresta25":
        pesoAresta25 = pesoAresta;
        break;
      default:
        break;
    }
  }

  public int[][] montarGrafo() {
    int[][] grafo = {
        // 0 1, 2, 3, 4, 5, 6
        { 0, I, I, I, I, I, I }, // 0
        { I, 0, I, I, I, I, I }, // 1
        { I, I, 0, I, I, I, I }, // 2
        { I, I, I, 0, I, I, I }, // 3
        { I, I, I, I, 0, I, I }, // 4
        { I, I, I, I, I, 0, I }, // 5
        { I, I, I, I, I, I, 0 }, // 6
    };

    grafo[0][1] = grafo[1][0] = pesoAresta01;
    grafo[0][2] = grafo[2][0] = pesoAresta02;
    grafo[1][4] = grafo[4][1] = pesoAresta14;
    grafo[1][3] = grafo[3][1] = pesoAresta13;
    grafo[2][3] = grafo[3][2] = pesoAresta23;
    grafo[3][5] = grafo[5][3] = pesoAresta35;
    grafo[3][4] = grafo[4][3] = pesoAresta34;
    grafo[4][6] = grafo[6][4] = pesoAresta46;
    grafo[5][6] = grafo[6][5] = pesoAresta56;
    grafo[2][5] = grafo[5][2] = pesoAresta25;

    return grafo;
  }

  public void limparVertices() {
    for (VBox vertice : vertices) {
      vertice.getStyleClass().remove("ativado");
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    vertices.add(vertice0);
    vertices.add(vertice1);
    vertices.add(vertice2);
    vertices.add(vertice3);
    vertices.add(vertice4);
    vertices.add(vertice5);
    vertices.add(vertice6);
  }

}
