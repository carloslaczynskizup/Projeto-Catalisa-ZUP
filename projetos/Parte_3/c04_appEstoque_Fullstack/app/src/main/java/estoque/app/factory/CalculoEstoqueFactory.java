package estoque.app.factory;

import org.springframework.stereotype.Component;

@Component
public class CalculoEstoqueFactory {

    public int calcularSomaEntrada(int quantidadeEstoque, int qtdMovimentacao) {
        return quantidadeEstoque + qtdMovimentacao;
    }

    public int calcularSubtracaoSaida(int quantidadeEstoque, int qtdMovimentacao) {
        return quantidadeEstoque - qtdMovimentacao;
    }
}
