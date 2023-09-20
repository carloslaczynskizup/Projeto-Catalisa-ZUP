package estoque.app.exceptions;

public class FornecedorNotFound extends RuntimeException {

    public FornecedorNotFound(Long id) {
        super("Fornecedor não encontrado: " + id + ". Favor verificar o id correto.");

    }
}
