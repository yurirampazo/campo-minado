package view;

import exception.ExplosaoException;
import exception.SairException;
import model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
  private Tabuleiro tabuleiro;
  private Scanner sc = new Scanner(System.in);


  public TabuleiroConsole(Tabuleiro tabuleiro) {
    this.tabuleiro = tabuleiro;

    executarJogo();
  }

  private void executarJogo() {
    try {
      boolean continuar = true;

      while (continuar) {
        cicloDoJogo();


        System.out.println("Deseja jogar outra partida? (S/N)");
        String resposta = sc.next();

        if ("N".equalsIgnoreCase(resposta)) {
          continuar = false;
        } else {
          tabuleiro.reiniciar();
        }
      }

    } catch (SairException e) {
      System.out.println("Tchau!");
    } finally {
      sc.close();
    }
  }

  private void cicloDoJogo() {
    try {

      while (!tabuleiro.objetivoAlcancado()) {
        System.out.println(tabuleiro);

        String digitado = capturarValorDigitado("Digite (X,Y) : ");

        Iterator<Integer> xy = Arrays.stream(digitado.split(","))
              .map(x -> Integer.parseInt(x.trim()))
              .iterator();

        digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
        if ("1".equals(digitado)) {
          tabuleiro.abrir(xy.next(), xy.next());
        } else if ("2".equals(digitado)) {
          tabuleiro.alterarMarcacao(xy.next(), xy.next());
        }
      }

      System.out.println(tabuleiro);
      System.out.println("Você ganhou!");
    } catch (ExplosaoException e) {
      System.out.println(tabuleiro);
      System.out.println("Você perdeu!");
    } finally {
      sc.close();
    }
  }

  private String capturarValorDigitado(String texto) {
    System.out.println(texto);
    String digitado = sc.nextLine();

    if("sair".equalsIgnoreCase(digitado)) {
      throw new SairException();
    }
    return digitado;
  }

}
