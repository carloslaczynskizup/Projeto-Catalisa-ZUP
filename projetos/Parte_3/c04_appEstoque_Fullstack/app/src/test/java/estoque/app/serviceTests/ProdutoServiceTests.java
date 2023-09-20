package estoque.app.serviceTests;

import estoque.app.model.FornecedorModel;
import estoque.app.model.ProdutoModel;
import estoque.app.repository.ProdutoRepository;
import estoque.app.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService produtoService;
    @Mock
    private ProdutoRepository produtoRepository;


    @Test
    // Teste para cadastrar um produto
    public void testeCadastrarProduto() {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNomeProduto("Produto 1");
        produtoModel.setCategoriaProduto("Categoria 1");
        produtoModel.setDescricaoProduto("Descrição 1");
        produtoModel.setQuantidadeEstoque(12);
        produtoModel.setTipoMovimentacao("Entrada");
        produtoModel.setMarca("Marca 1");
        produtoModel.setPrecoVenda(12.99);
        produtoModel.setFornecedor(new FornecedorModel
                (1L, null, null, null, null,
                        null, null, null,
                        null, null));

        when(produtoRepository.save(any(ProdutoModel.class))).thenReturn(produtoModel);

        ProdutoModel retorno = produtoService.cadastrarProduto(produtoModel);

        verify(produtoRepository, times(1)).save(produtoModel);

        assertEquals(produtoModel, retorno, "O resultado não é o esperado");

        System.out.println();
        System.out.println(retorno.getNomeProduto()
                + " - " + retorno.getPrecoVenda()
                + " - " + retorno.getQuantidadeEstoque()
                + " - " + retorno.getTipoMovimentacao()
                + " - " + retorno.getMarca()
                + " - " + retorno.getCategoriaProduto()
                + " - " + retorno.getDescricaoProduto()
                + " - " + retorno.getFornecedor().getId());
        System.out.println();

    }

    @Test
    // Teste para listar todos os produtos
    public void testeListarTodosProdutos() {

        //Criando uma lista simulada de produtos para ser retornada pelo mock do repository
        List<ProdutoModel> produtoModel = new ArrayList<>();
        produtoModel.add(new ProdutoModel());
        produtoModel.add(new ProdutoModel());
        produtoModel.add(new ProdutoModel());
        produtoModel.add(new ProdutoModel());
        produtoModel.add(new ProdutoModel());

        //Configurando o mock do repository para retornar a lista simulada quando
        // o método findAll() for chamado
        when(produtoRepository.findAll()).thenReturn(produtoModel);

        //Chamando o método a ser testado
        List<ProdutoModel> retorno = produtoService.listarTodosProdutos();

        //Verificando se o método findAll() do repository foi chamado exatamente uma vez
        verify(produtoRepository, times(1)).findAll();

        //Verificando se a quantidade de produtos na lista de resultado é igual a 5
        assertEquals(5, retorno.size(),
                "A quantidade(5) de produtos da listagem não é a esperada");

        //Printa no console os produtos "retornados"
        System.out.println();
        System.out.println(retorno.get(0).getId()
                + " - " + retorno.get(1).getId()
                + " - " + retorno.get(2).getId()
                + " - " + retorno.get(3).getId()
                + " - " + retorno.get(4).getId());
        System.out.println();
    }

    @Test
    // Teste para listar todos os produtos por ID
    public void testeListarTodosProdutosPorId() {

        //Criação de um objeto ProdutoModel simulado
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(8L);

        //Configurando o mock do repository para retornar o objeto simulado
        when(produtoRepository.findById(8L)).thenReturn(java.util.Optional.of(produtoModel));

        //Chamando o método a ser testado
        ProdutoModel retorno = produtoService.listarProdutoPorId(8L);

        //Verificando se o método findById() do repository foi chamado exatamente vezes desejada
        verify(produtoRepository, times(1)).findById(8L);

        //Verificando se a quantidade de produtos na lista de resultado é igual a 2
        assertEquals(produtoModel, retorno, "A quantidade(1) de produtos não é a esperada");

        //Printa no console os produtos "retornados"
        System.out.println();
        System.out.println("O Id do produto é: ID " + retorno.getId());
        System.out.println();
    }

    //Teste para atualizar um produto
    @Test
    public void testAtualizarProduto() {
        //Atualiza um produto pelo ID fornecido e retorna o produto atualizado

        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(1L);
        produtoModel.setNomeProduto("Produto Atualizado");
        produtoModel.setMarca("Nova Marca");
        produtoModel.setDescricaoProduto("Nova Descrição");
        produtoModel.setCategoriaProduto("Nova Categoria");
        produtoModel.setPrecoVenda(20.0);
        produtoModel.setQuantidadeEstoque(50);
        produtoModel.setTipoMovimentacao("Saída");
        produtoModel.setQtdMovimentacao(10);

        // Configuração do mock do produtoRepository para retornar o produto simulado após a atualização
        when(produtoRepository.save(produtoModel)).thenReturn(produtoModel);

        // Chama o método a ser testado
        ProdutoModel resultado = produtoService.atualizarProduto(produtoModel);

        // Verifica se o método save do mock do produtoRepository foi chamado exatamente uma vez
        verify(produtoRepository, times(1)).save(produtoModel);

        // Verifica se o resultado do método é o mesmo que o produto simulado
        assertEquals(produtoModel, resultado, "O produto retornado não coincide com o esperado após a atualização.");

        //Printar no console os produtos "retornados"
        System.out.println();
        System.out.println(resultado.getNomeProduto()
                + " - " + resultado.getMarca()
                + " - " + resultado.getPrecoVenda()
                + " - " + resultado.getQuantidadeEstoque()
                + " - " + resultado.getTipoMovimentacao()
                + " - " + resultado.getQtdMovimentacao()
                + " - " + resultado.getId()
                + " - " + resultado.getDescricaoProduto()
                + " - " + resultado.getCategoriaProduto()
        );
        System.out.println();
    }

    //Teste para simular atualizar a quantidade de estoque
    @Test
    public void testAtualizarQtdEstoque() {
        //Criar um produto simulado
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(1L);
        produtoModel.setTipoMovimentacao("Entrada");
        produtoModel.setQuantidadeEstoque(10);
        produtoModel.setQtdMovimentacao(50);

        //Nova quantidade de estoque
        int qtdEstoque = produtoModel.getQuantidadeEstoque() + produtoModel.getQtdMovimentacao();
        produtoModel.setQuantidadeEstoque(qtdEstoque);

        //Configuração do mock do produtoRepository para retornar o produto simulado
        when(produtoRepository.save(produtoModel)).thenReturn(produtoModel);

        //Chama o método a ser testado
        ProdutoModel resultado = produtoService.atualizarQtdEstoque(produtoModel);

        //Verifica se o metodo save do mock do produtoRepository foi chamado exatamente uma vez
        verify(produtoRepository, times(1)).save(produtoModel);

        //Verifica se o resultado do metodo é o mesmo que o produto simulado
        assertEquals(qtdEstoque, resultado.getQuantidadeEstoque(),
                "A quantidade de estoque não é a esperada");

        //Printar no console os produtos "retornados"
        System.out.println();
        System.out.println("A nova quantidade de estoque é: " + resultado.getQuantidadeEstoque());
        System.out.println();
    }

    //Teste para deletar um produto
    @Test
    public void testDeletarProduto() {

        Long id = 1L;

        produtoService.deletarProduto(id);

        verify(produtoRepository, times(1)).deleteById(id);

        System.out.println();
        System.out.println("Produto com ID " + id + " deletado com sucesso!");
        System.out.println();
    }

    //Teste para listar todos os produtos conforme DTO
    @Test
    public void testListarTodosProdutosDTO() {
        // Criação de produtos simulados para retornar no teste
        List<ProdutoModel> produtos = new ArrayList<>();
        produtos.add(new ProdutoModel());
        produtos.add(new ProdutoModel());

        // Configuração do mock do produtoRepository para retornar os produtos simulados
        when(produtoRepository.findAll()).thenReturn(produtos);

        // Chama o método a ser testado
        List<ProdutoModel> resultado = produtoService.listarTodosProdutosDTO();

        // Verifica se o método findAll do mock do produtoRepository foi chamado exatamente uma vez
        verify(produtoRepository, times(1)).findAll();

        // Verifica se o resultado do método é a mesma lista de produtos simulados
        assertEquals(produtos, resultado, "A lista de produtos não coincide com a esperada.");

        //Printar no console os produtos "retornados"
        System.out.println();
        System.out.println(resultado.get(0).getId()
                + " - " + resultado.get(0).getNomeProduto()
                + " - " + resultado.get(1).getId()
                + " - " + resultado.get(1).getNomeProduto());
        System.out.println();
    }
}

