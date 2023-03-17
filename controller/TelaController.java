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
  private TextField pesoAresta01, pesoAresta02, pesoAresta14, pesoAresta13, pesoAresta23, pesoAresta34, pesoAresta35,
      pesoAresta46, pesoAresta56, pesoAresta25;
  @FXML
  private VBox vertice1, vertice2, vertice3, vertice4, vertice5, vertice6, vertice0;
  @FXML
  private TextField textFieldOrigem, textFieldDestino;

  private List<VBox> vertices = new ArrayList<>();
  private List<Line> arestas = new ArrayList<>();

  private int I = Integer.MAX_VALUE;

  // private int pesoAresta01, pesoAresta02, pesoAresta14, pesoAresta13,
  // pesoAresta23, pesoAresta34,
  // pesoAresta35, pesoAresta46, pesoAresta56, pesoAresta25;

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
      limpaGrafo();

      int ultimoVertice = numOrigem;
      for (int vertice : resultado) {
        int index = resultado.indexOf(vertice);

        vertices.get(vertice).getStyleClass().add("ativado");

        if (ultimoVertice == 0 && vertice == 1 || ultimoVertice == 1 && vertice == 0)
          aresta01.getStyleClass().add("ativado");
        if (ultimoVertice == 0 && vertice == 2 || ultimoVertice == 2 && vertice == 0)
          aresta02.getStyleClass().add("ativado");
        if (ultimoVertice == 1 && vertice == 3 || ultimoVertice == 3 && vertice == 1)
          aresta13.getStyleClass().add("ativado");
        if (ultimoVertice == 1 && vertice == 4 || ultimoVertice == 4 && vertice == 1)
          aresta14.getStyleClass().add("ativado");
        if (ultimoVertice == 2 && vertice == 3 || ultimoVertice == 3 && vertice == 2)
          aresta23.getStyleClass().add("ativado");
        if (ultimoVertice == 2 && vertice == 5 || ultimoVertice == 5 && vertice == 2)
          aresta25.getStyleClass().add("ativado");
        if (ultimoVertice == 3 && vertice == 4 || ultimoVertice == 4 && vertice == 3)
          aresta34.getStyleClass().add("ativado");
        if (ultimoVertice == 3 && vertice == 5 || ultimoVertice == 5 && vertice == 3)
          aresta35.getStyleClass().add("ativado");
        if (ultimoVertice == 4 && vertice == 6 || ultimoVertice == 6 && vertice == 4)
          aresta46.getStyleClass().add("ativado");
        if (ultimoVertice == 5 && vertice == 6 || ultimoVertice == 6 && vertice == 5)
          aresta56.getStyleClass().add("ativado");

        ultimoVertice = vertice;

        System.out.print(vertice);
        if (index != resultado.size() - 1)
          System.out.print(" -> ");
        else
          System.out.println();

      }
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
        pesoAresta01.setText(pesoAresta + "");
        break;
      case "aresta02":
        pesoAresta02.setText(pesoAresta + "");
        break;
      case "aresta14":
        pesoAresta14.setText(pesoAresta + "");
        break;
      case "aresta13":
        pesoAresta13.setText(pesoAresta + "");
        break;
      case "aresta23":
        pesoAresta23.setText(pesoAresta + "");
        break;
      case "aresta34":
        pesoAresta34.setText(pesoAresta + "");
        break;
      case "aresta35":
        pesoAresta35.setText(pesoAresta + "");
        break;
      case "aresta46":
        pesoAresta46.setText(pesoAresta + "");
        break;
      case "aresta56":
        pesoAresta56.setText(pesoAresta + "");
        break;
      case "aresta25":
        pesoAresta25.setText(pesoAresta + "");
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

    grafo[0][1] = grafo[1][0] = Integer.parseInt(pesoAresta01.getText());
    grafo[0][2] = grafo[2][0] = Integer.parseInt(pesoAresta02.getText());
    grafo[1][4] = grafo[4][1] = Integer.parseInt(pesoAresta14.getText());
    grafo[1][3] = grafo[3][1] = Integer.parseInt(pesoAresta13.getText());
    grafo[2][3] = grafo[3][2] = Integer.parseInt(pesoAresta23.getText());
    grafo[3][5] = grafo[5][3] = Integer.parseInt(pesoAresta35.getText());
    grafo[3][4] = grafo[4][3] = Integer.parseInt(pesoAresta34.getText());
    grafo[4][6] = grafo[6][4] = Integer.parseInt(pesoAresta46.getText());
    grafo[5][6] = grafo[6][5] = Integer.parseInt(pesoAresta56.getText());
    grafo[2][5] = grafo[5][2] = Integer.parseInt(pesoAresta25.getText());

    return grafo;
  }

  public void limpaGrafo() {
    for (VBox vertice : vertices) {
      vertice.getStyleClass().remove("ativado");
    }

    for (Line aresta : arestas) {
      aresta.getStyleClass().remove("ativado");
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
    arestas.add(aresta01);
    arestas.add(aresta02);
    arestas.add(aresta14);
    arestas.add(aresta13);
    arestas.add(aresta23);
    arestas.add(aresta25);
    arestas.add(aresta34);
    arestas.add(aresta35);
    arestas.add(aresta46);
    arestas.add(aresta56);
    arestas.add(aresta25);
  }

}
