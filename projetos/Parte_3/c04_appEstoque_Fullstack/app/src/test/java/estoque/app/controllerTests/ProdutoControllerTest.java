package estoque.app.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import estoque.app.model.FornecedorModel;
import estoque.app.model.ProdutoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Este é um teste de integração do Spring Boot.
@SpringBootTest

//Configura automaticamente o MockMvc, que é usado para simular solicitações HTTP.
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    //Injeta uma instância de MockMvc para usar nos testes.
    private MockMvc mockMvc;

    @Autowired
    //Injeta uma instância de ObjectMapper, que é usado para converter objetos em JSON.
    private ObjectMapper objectMapper;

    @Test
    public void testCadastrarProduto() throws Exception {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNomeProduto("Produto de Teste");
        produtoModel.setCategoriaProduto("Categoria de Teste");
        produtoModel.setDescricaoProduto("Descrição de Teste");
        produtoModel.setMarca("Marca de Teste");
        produtoModel.setPrecoVenda(12.99);
        produtoModel.setQuantidadeEstoque(12);
        produtoModel.setTipoMovimentacao("Entrada");
        produtoModel.setDataCadastro(LocalDateTime.now());

        //mockMvc.perform: Inicia uma solicitação HTTP simulada usando o MockMvc.
        //MockMvcRequestBuilders: Classe usada para construir diferentes tipos de solicitações
        //(GET, POST, PUT, DELETE, etc.).
        mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                //.contentType(...): Define o tipo de conteúdo da solicitação (no caso, JSON).
                        .contentType(MediaType.APPLICATION_JSON)
                //.content(...): Define o conteúdo da solicitação (aqui, o objeto produtoModel convertido em JSON).
                        .content(objectMapper.writeValueAsString(produtoModel)))
                //.andExpect(status().isOk()): Verifica se o status da resposta da solicitação é "OK" (código 200).
                .andExpect(status().isOk())

                .andDo(print());
    }

    @Test
    public void testListarTodosProdutos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos"))
                .andExpect(status().isOk())

                .andDo(print());
    }

    @Test
    public void testListarProdutoPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/{id}", 1L))
                .andExpect(status().isOk())

                .andDo(print());
    }

    @Test
    public void testAtualizarProduto() throws Exception {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNomeProduto("Produto Atualizado");

        mockMvc.perform(MockMvcRequestBuilders.put("/produtos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoModel)))
                .andExpect(status().isOk())

                .andDo(print());
    }

    @Test
    public void testAtualizarQtdEstoque() throws Exception {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setTipoMovimentacao("Entrada");
        produtoModel.setQtdMovimentacao(10);

        mockMvc.perform(MockMvcRequestBuilders.put("/produtos/estoque/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoModel)))
                .andExpect(status().isOk())

                .andDo(print());
    }

    @Test
    public void testDeletarProduto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", 69L))
                .andExpect(status().isOk())

                .andDo(print());
    }

    @Test
    public void testListarTodosProdutosDTO() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/dto"))
                .andExpect(status().isOk())

                .andDo(print());
    }
}

