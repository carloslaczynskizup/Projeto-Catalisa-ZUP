package estoque.app.exceptions;

public class ProdutoNotFound extends RuntimeException {

    public ProdutoNotFound(Long id) {
        super("Produto não encontrado: " + id + ". Favor verificar o id correto.");

    }


}
