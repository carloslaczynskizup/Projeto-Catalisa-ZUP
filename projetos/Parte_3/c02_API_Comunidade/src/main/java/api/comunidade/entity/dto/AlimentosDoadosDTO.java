package api.comunidade.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentosDoadosDTO {

    private String tipoAlimento;
    private String descricaoAlimento;
    private int quantidadeDoacao;
    private String tipoUnidade;

}
