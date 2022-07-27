package model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

  private int linhas;
  private int colunas;
  private int minas;

  private final List<Campo> CAMPOS = new ArrayList<>();

  public Tabuleiro(int linhas, int colunas, int minas) {
    this.linhas = linhas;
    this.colunas = colunas;
    this.minas = minas;

    gerarCampos();
    associarOsVizinhos();
    sortearAsMinas();
  }

  public void abrir(int linha, int coluna) {
    CAMPOS.parallelStream()
          .filter(x -> x.getLINHA() == linha && x.getCOLUNA() == coluna)
          .findFirst()
          .ifPresent(Campo::abrir);
  }

  public void alterarMarcacao(int linha, int coluna) {
    CAMPOS.parallelStream()
          .filter(x -> x.getLINHA() == linha && x.getCOLUNA() == coluna)
          .findFirst()
          .ifPresent(Campo::alterarMarcacao);
  }

  private void gerarCampos() {
    for (int i = 0; i < linhas; i++) {
      for (int j = 0; j < colunas; j++) {
        CAMPOS.add(new Campo(i, j));
      }
    }
  }

  private void associarOsVizinhos() {
    for (Campo c1 : CAMPOS) {
      for (Campo c2 : CAMPOS) {
        c1.adicionarVizinho(c2);
      }
    }
  }

  private void sortearAsMinas() {
    long minasArmadas = 0;

    do {
      minasArmadas = CAMPOS.stream().filter(Campo::isMinado).count();
      int aleatorio = (int) (Math.random() * CAMPOS.size());
      CAMPOS.get(aleatorio).setMinado(true);
    } while (minasArmadas < minas);
  }

  public boolean objetivoAlcancado() {
    return CAMPOS.stream().allMatch(Campo::objetivoAlcancado);
  }

  public void reiniciar() {
    CAMPOS.forEach(Campo::reiniciar);
    sortearAsMinas();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    int l = 0;

    for (int i = 0; i < linhas; i++) {
      for (int j = 0; j < colunas; j++) {
        sb.append(" ");
        sb.append(CAMPOS.get(l));
        sb.append(" ");
        l++;
      }
      sb.append("\n");
    }


    return sb.toString();
  }
}
