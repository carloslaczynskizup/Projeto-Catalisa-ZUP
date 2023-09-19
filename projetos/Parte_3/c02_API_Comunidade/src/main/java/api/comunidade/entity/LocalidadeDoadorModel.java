package api.comunidade.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "localidade_doador")
public class LocalidadeDoadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doador_nome", length = 100)
    private String doadorNome;

    @Column(name = "doador_documento", length = 50)
    private String doadorDocumento;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "endereco_local")
    private String enderecoLocal;
}
