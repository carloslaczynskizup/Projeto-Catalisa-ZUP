package estoque.app.controller;


import estoque.app.exceptions.ProdutoNotFound;
import estoque.app.factory.CalculoEstoqueFactory;
import estoque.app.model.ProdutoModel;
import estoque.app.model.dto.ProdutoListDTO;
import estoque.app.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@Tag(name = "Produtos", description = "Endpoint para controle de produtos.")
public class ProdutoController {

    private ProdutoService produtoService;

    private CalculoEstoqueFactory calculoEstoqueFactory;

    @Autowired
    public ProdutoController(ProdutoService produtoService,
                             CalculoEstoqueFactory calculoEstoqueFactory) {

        this.produtoService = produtoService;
        this.calculoEstoqueFactory = calculoEstoqueFactory;

    }

    //Endpoint para cadastrar um novo produto

    @PostMapping
    @Operation(summary = "Cadastra um novo produto",
            description = "Endpoint para cadastrar um novo produto.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso!")})

    public ProdutoModel cadastrarProduto(@RequestBody ProdutoModel produtoModel) {

        produtoModel.setDataCadastro(LocalDateTime.now());

        return produtoService.cadastrarProduto(produtoModel);
    }

    //Endpoint para listar todos os produtos
    @GetMapping
    @Operation(summary = "Listagem de todos os produtos cadastrados.",
            description = "Endpoint para listar todos os produtos.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso!")})

    public List<ProdutoModel> listarTodosProdutos() {
        return produtoService.listarTodosProdutos();
    }

    //Endpoint para listar um produto por id
    @GetMapping("/{id}")
    @Operation(summary = "Lista um produto cadastrado por id.",
            description = "Endpoint para listar um produto por id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto listado com sucesso")})
    public ProdutoModel listarProdutoPorId(@PathVariable Long id) {
        ProdutoModel produtoExistente = produtoService.listarProdutoPorId(id);

        if (produtoExistente == null) {
            throw new ProdutoNotFound(id);

        }
        return produtoExistente;
    }

    //Endpoint para atualizar um produto
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza qualquer dado de um produto cadastrado.",
            description = "Endpoint para atualizar qualquer dado de um produto cadastrado." + "\n" +
                    "Pode preencher para modificar todos os campos ou apenas alguns ou somente um."
            , method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")})
    public ProdutoModel atualizarProduto(@PathVariable Long id,
                                         @RequestBody ProdutoModel produtoModel) {
        ProdutoModel produtoExistente = produtoService.listarProdutoPorId(id);
        if (produtoExistente != null) {
            produtoExistente.setId(id);

            if (produtoModel.getNomeProduto() != null) {
                produtoExistente.setNomeProduto(produtoModel.getNomeProduto());
            }
            if (produtoModel.getMarca() != null) {
                produtoExistente.setMarca(produtoModel.getMarca());
            }
            if (produtoModel.getDescricaoProduto() != null) {
                produtoExistente.setDescricaoProduto(produtoModel.getDescricaoProduto());
            }
            if (produtoModel.getCategoriaProduto() != null) {
                produtoExistente.setCategoriaProduto(produtoModel.getCategoriaProduto());
            }
            if (produtoModel.getPrecoVenda() != 0.0) {
                produtoExistente.setPrecoVenda(produtoModel.getPrecoVenda());
            }
            if (produtoModel.getQuantidadeEstoque() != 0) {
                produtoExistente.setQuantidadeEstoque(produtoModel.getQuantidadeEstoque());
            }
            if (produtoModel.getTipoMovimentacao() != null) {
                produtoExistente.setTipoMovimentacao(produtoModel.getTipoMovimentacao());
            }
            if (produtoModel.getQtdMovimentacao() != 0) {
                produtoExistente.setQtdMovimentacao(produtoModel.getQtdMovimentacao());
            }
            if (produtoModel.getFornecedor() != null) {
                produtoExistente.setFornecedor(produtoModel.getFornecedor());
            }

        } else {

            throw new ProdutoNotFound(id);
        }

        return produtoService.atualizarProduto(produtoExistente);

    }

    //TESTE
    @PutMapping("estoque/{id}")
    @Operation(summary = "Atualiza a quantidade em estoque de um produto",
            description = "Endpoint para atualizar o estoque de um produto, conforme o tipo de movimentação" +
                    " (entrada ou saída). Então, a quantidade em estoque é somada ou subtraída conforme o tipo de movimentação." +
           "Somente necessário utilizar dois atributos: tipoMovimentacao e qtdMovimentacao." +
           "Exemplos: " + "tipoMovimentacao: entrada " + "qtdMovimentacao: 10 " + " ou " +
            "tipoMovimentacao: saída "  + "qtdMovimentacao: 5 " +
            "\n  " +
            "\n  " +
            "{" +
            "  \n" +
            "  \n" +
            "  \"tipoMovimentacao\": \"Saída\",\n" +
            "  \"qtdMovimentacao\": 60\n" +
            "  \n" +
            " \n" +
            "}",

            method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso")})
    public ProdutoModel atualizarQtdEstoque(@PathVariable Long id, @RequestBody ProdutoModel produtoModel) {
        ProdutoModel produtoExistente = produtoService.listarProdutoPorId(id);

        if (produtoExistente != null) {
            produtoExistente.setQtdMovimentacao(produtoModel.getQtdMovimentacao());
            produtoExistente.setTipoMovimentacao(produtoModel.getTipoMovimentacao());

            int qtdMovimentacao = produtoModel.getQtdMovimentacao();
            int novaQtd;

            if (produtoModel.getTipoMovimentacao().equalsIgnoreCase("entrada")) {
                novaQtd = calculoEstoqueFactory.calcularSomaEntrada(produtoExistente.getQuantidadeEstoque(),
                        qtdMovimentacao);
            } else if (produtoModel.getTipoMovimentacao().equalsIgnoreCase("saída")) {
                novaQtd = calculoEstoqueFactory.calcularSubtracaoSaida(produtoExistente.getQuantidadeEstoque(),
                        qtdMovimentacao);
            } else {
                throw new IllegalArgumentException("Tipo de movimentação inválida. Tente novamente!");

            }

            produtoExistente.setQuantidadeEstoque(novaQtd);


        } else {
            throw new ProdutoNotFound(id);
        }

        return produtoService.atualizarQtdEstoque(produtoExistente);

    }

    //Endpoint para deletar um produto
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto cadastrado por id.",
            description = "Endpoint para deletar um produto.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso")})
    public void deletarProduto(@PathVariable Long id) {
        if (produtoService.listarProdutoPorId(id) == null) {
            throw new ProdutoNotFound(id);
        } else {
            produtoService.deletarProduto(id);

        }

    }

    //Endpoint para listar todos os produtos pela classe DTO
    @GetMapping("/dto")
    @Operation(summary = "Listagem de todos os produtos, conforme limitações do DTO.",
            description = "Endpoint para listar todos os produtos, conforme limitações do DTO." +
                    "\n" + "Só será retornado os campos id, nomeProduto, marca, quantidadeEstoque.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso")})
    public List<ProdutoListDTO> listarTodosProdutosDTO() {

        List<ProdutoModel> produtos = produtoService.listarTodosProdutosDTO();
        List<ProdutoListDTO> dtos = new ArrayList<>();
        for (ProdutoModel produto : produtos) {
            ProdutoListDTO dto = new ProdutoListDTO();
            dto.setId(produto.getId());
            dto.setNomeProduto(produto.getNomeProduto());
            dto.setMarca(produto.getMarca());
            dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            dtos.add(dto);
        }
        return dtos;
    }

}
