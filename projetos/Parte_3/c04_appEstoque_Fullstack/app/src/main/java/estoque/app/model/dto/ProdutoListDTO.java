package estoque.app.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoListDTO {

    private Long id;
    private String nomeProduto;
    private String marca;
    private int quantidadeEstoque;

}
