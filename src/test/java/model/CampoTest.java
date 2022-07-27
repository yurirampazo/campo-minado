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
  void testAdicionarVizinhoFalse() {

    Campo vizinho = new Campo(1, 1);

    boolean resultado = campo.adicionarVizinho(vizinho);

    assertFalse(resultado, "Deve ser verdadeiro");
  }

  @Test
  void testeAlterarMarcacao() {
    assertFalse(campo.isAberto(), "Deve ser falso");
  }

  @Test
  void testAbrirMarcado() {
    campo.alterarMarcacao();
    assertFalse( campo.abrir(), "Deve ser falso");
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
}