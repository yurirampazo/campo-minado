package model;

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

}