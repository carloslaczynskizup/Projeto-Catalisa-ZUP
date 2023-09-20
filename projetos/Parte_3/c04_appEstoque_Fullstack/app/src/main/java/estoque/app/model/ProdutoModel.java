package estoque.app.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto", length = 100, nullable = false)
    private String nomeProduto;
    @Column(name = "marca", length = 50, nullable = false)
    private String marca;
    @Column(name = "descricao_produto", length = 200, nullable = false)
    private String descricaoProduto;
    @Column(name = "categoria_produto", length = 50, nullable = false)
    private String categoriaProduto;

    @Column(name = "preco_venda", length = 15, nullable = false)
    private double precoVenda;
    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    @Column(name = "tipo_movimentacao", length = 10, nullable = false)
    private String tipoMovimentacao;
    @Column(name = "qtd_movimentacao")
    private int qtdMovimentacao;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private FornecedorModel fornecedor;

}
