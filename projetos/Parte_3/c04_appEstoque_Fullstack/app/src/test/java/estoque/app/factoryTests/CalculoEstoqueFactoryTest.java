package estoque.app.factoryTests;


import estoque.app.factory.CalculoEstoqueFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculoEstoqueFactoryTest {

    @Autowired
    private CalculoEstoqueFactory calculoEstoqueFactory;

        //Teste para validar o retorno da soma esperada no estoque
    @Test
    public void calcularSomaEntrada() {

        int quantidadeEstoque = 200;
        int qtdMovimentacao = 50;

        int retornoEsperado = calculoEstoqueFactory.calcularSomaEntrada(quantidadeEstoque, qtdMovimentacao);

        assertEquals(retornoEsperado, 250, "A quantidade de soma para o estoque não é a esperada");

        System.out.println();
        System.out.println("Quantidade de estoque é: " + quantidadeEstoque);
        System.out.println("Quantidade de movimentação(entrada) é: " + qtdMovimentacao);
        System.out.println("A nova quantidade(soma) de estoque é: " + retornoEsperado);
        System.out.println();
    }

    //Teste para validar o retorno da subtração esperada no estoque
    @Test
    public void calcularSubtracaoSaida() {

        int quantidadeEstoque = 200;
        int qtdMovimentacao = 50;

        int retornoEsperado = calculoEstoqueFactory.calcularSubtracaoSaida(quantidadeEstoque, qtdMovimentacao);

        assertEquals(retornoEsperado, 150, "A quantidade de subtração para o estoque não é a esperada");

        System.out.println();
        System.out.println("Quantidade de estoque é: " + quantidadeEstoque);
        System.out.println("Quantidade de movimentação(saída) é: " + qtdMovimentacao);
        System.out.println("A nova quantidade(subtração) de estoque é: " + retornoEsperado);
        System.out.println();
    }


}
