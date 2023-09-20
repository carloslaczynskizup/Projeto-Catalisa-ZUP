package estoque.app.service;

import estoque.app.model.ProdutoModel;
import estoque.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;


    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel cadastrarProduto(ProdutoModel produtoModel) {

        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel listarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public ProdutoModel atualizarProduto(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public ProdutoModel atualizarQtdEstoque(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    //Método para listar todos os produtos pela classe DTO
    public List<ProdutoModel> listarTodosProdutosDTO() {
        return produtoRepository.findAll();
    }

}
