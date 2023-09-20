package estoque.app.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fornecedores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj", length = 14, nullable = false)
    private String cnpj;
    @Column(name = "razao_social", length = 100, nullable = false)
    private String razaoSocial;
    @Column(name = "nome_fantasia", length = 100, nullable = false)
    private String nomeFantasia;
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "categoria_produto", length = 50, nullable = false)
    private String categoriaProduto;
    @Column(name = "nome_produto", length = 100, nullable = false)
    private String nomeProduto;
    @Column(name = "nome_contato", length = 50, nullable = false)
    private String nomeContato;
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;
    @Column(name = "endereco", length = 100, nullable = false)
    private String endereco;


}
