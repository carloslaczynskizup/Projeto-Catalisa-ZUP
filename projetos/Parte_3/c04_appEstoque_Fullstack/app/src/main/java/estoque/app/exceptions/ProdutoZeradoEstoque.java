package estoque.app.exceptions;

public class ProdutoZeradoEstoque extends RuntimeException {

    public ProdutoZeradoEstoque(Long id) {
        super("Produto está zerado no estoque: " + id + ". Favor verificar reposição do produto.");

    }
}
