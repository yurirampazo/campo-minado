import model.Tabuleiro;
import view.TabuleiroConsole;

public class Application {
  public static void main(String[] args) {

    Tabuleiro tabuleiro = new Tabuleiro(8, 8, 8);
    new TabuleiroConsole(tabuleiro);



  }
}
