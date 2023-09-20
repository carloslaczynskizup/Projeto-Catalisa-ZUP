package estoque.app.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorListDTO {

    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
}
