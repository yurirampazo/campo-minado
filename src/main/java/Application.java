import model.Tabuleiro;

public class Application {
  public static void main(String[] args) {

    Tabuleiro tabuleiro = new Tabuleiro(8, 8, 8);

    tabuleiro.abrir(3, 3);
    tabuleiro.alterarMarcacao(4, 4);
    tabuleiro.alterarMarcacao(4,5);

    System.out.println(tabuleiro);
  }
}
