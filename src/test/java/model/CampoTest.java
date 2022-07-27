package model;

import exception.ExplosaoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {
  Campo campo;

  @BeforeEach
  void setUp() {
    campo = new Campo(3, 3);
  }

  @AfterEach
  void tearDown() {
    campo = null;
  }

  @Test
  void testAdicionarVizinho() {
    Campo vizinho = new Campo(3, 2);

    boolean resultado = campo.adicionarVizinho(vizinho);

    assertTrue(resultado, "Deve ser verdadeiro");
  }

  @Test
  void testAdicionarVizinho2() {
    Campo vizinho = new Campo(2, 3);

    boolean resultado = campo.adicionarVizinho(vizinho);

    assertTrue(resultado, "Deve ser verdadeiro");
  }

  @Test
  void testAdicionarVizinhoFalse() {

    Campo vizinho = new Campo(1, 1);

    boolean resultado = campo.adicionarVizinho(vizinho);

    assertFalse(resultado, "Deve ser falso");
  }

  @Test
  void testAdicionarVizinhoDiagonal() {

    Campo vizinho = new Campo(2, 2);

    boolean resultado = campo.adicionarVizinho(vizinho);

    assertTrue(resultado, "Deve ser verdadeiro");
  }

  @Test
  void testeAlterarMarcacao() {
    assertFalse(campo.isAberto(), "Deve ser falso");
  }

  @Test
  void testAbrirMarcado() {
    campo.alterarMarcacao();
    assertFalse(campo.abrir(), "Deve ser falso");
  }

  @Test
  void abrirNaoMarcado() {
    campo.vizinhancaSegura();
    assertTrue(campo.abrir());
  }

  @Test
  void testMinado() {
    campo.setMinado(true);
    assertThrows(ExplosaoException.class, () -> campo.abrir(),
          "Deve jogar a exceção personalizada ExplosãoException.");
  }

  @Test
  void abrirComVizinhos() {
    Campo vizinho1 = new Campo(2, 2);
    Campo vizinho2 = new Campo(1, 1);

    vizinho1.adicionarVizinho(vizinho2);
    campo.adicionarVizinho(vizinho1);
    campo.abrir();

    assertTrue(vizinho2.isAberto()
          && vizinho1.isAberto(), "Deve ser verdadeiro");
  }

  @Test
  void abrirComVizinhosMinados() {
    Campo vizinho1 = new Campo(2, 2);
    Campo vizinho2 = new Campo(1, 1);
    vizinho2.setMinado(true);

    vizinho1.adicionarVizinho(vizinho2);
    campo.adicionarVizinho(vizinho1);
    campo.abrir();

    assertTrue(vizinho1.isAberto() && !vizinho2.isAberto());
  }

  @Test
  void testObjetivoAlcancado() {
    campo.setMinado(false);

    Campo vizinho = new Campo(2, 3);
    vizinho.setMinado(false);
    vizinho.alterarMarcacao();
    campo.abrir();

    assertTrue(campo.objetivoAlcancado());
  }

  @Test
  void testMinaVizinha() {
    campo.setMinado(false);

    Campo vizinho = new Campo(2, 3);
    campo.adicionarVizinho(vizinho);
    vizinho.setMinado(true);
    campo.abrir();

    assertEquals(1L, campo.minasNaVizinhanca());
  }

  @Test
  void testReiniciar() {
    campo.reiniciar();

    assertFalse(campo.isMinado()
          && campo.isAberto()
          && campo.isMarcado());
  }


  @Test
  void testToString() {
    //Teste campo marcado
    campo.alterarMarcacao();
    assertEquals("x", campo.toString());

    //Teste Campo minado
    campo.alterarMarcacao();
    campo.abrir();
    campo.setMinado(true);

    assertEquals("*", campo.toString());

    //Teste campo vazio
    campo.reiniciar();
    campo.abrir();
    assertEquals(" ", campo.toString());

    //teste campo com campos vizinhos minados
    Campo vizinho = new Campo(2, 3);
    vizinho.setMinado(true);
    campo.adicionarVizinho(vizinho);

    assertEquals("1", campo.toString());

    //teste campo não aberto
    campo.reiniciar();
    assertEquals("?", campo.toString());
  }

}