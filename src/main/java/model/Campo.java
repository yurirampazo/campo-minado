package model;

import exception.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
  private boolean minado = false;
  private boolean marcado = false;
  private boolean aberto = false;

  private final int LINHA;
  private final int COLUNA;

  private List<Campo> vizinhos = new ArrayList<>();

  public Campo(int linha, int coluna) {
    this.LINHA = linha;
    this.COLUNA = coluna;
  }
  public boolean isMinado() {
    return minado;
  }

  public boolean isMarcado() {
    return marcado;
  }

  public boolean isAberto() {
    return aberto;
  }

  public void setMinado(boolean minado) {
    this.minado = minado;
  }

  boolean adicionarVizinho(Campo vizinho) {
    boolean linhaDiferente = LINHA != vizinho.LINHA;
    boolean coluhaDiferente = COLUNA != vizinho.COLUNA;
    boolean diagonal = linhaDiferente && coluhaDiferente;

    int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
    int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA);
    int deltaGeral = deltaColuna + deltaLinha;

    if (deltaGeral == 1 && !diagonal
          || deltaGeral == 2 && diagonal) {
      vizinhos.add(vizinho);
      return true;
    }
    return false;
  }

  void alterarMarcacao() {
    if (!aberto) {
      marcado = !marcado;
    }
  }

  boolean abrir() {

    if (!aberto && !marcado) {
      aberto = true;

      if(minado) {
        throw new ExplosaoException();
      }
      if (vizinhancaSegura()) {
        vizinhos.forEach(Campo::abrir);
      }
      return true;
    }
    return false;
  }

  boolean vizinhancaSegura() {
    return vizinhos.stream().noneMatch(x -> x.minado);
  }
}
