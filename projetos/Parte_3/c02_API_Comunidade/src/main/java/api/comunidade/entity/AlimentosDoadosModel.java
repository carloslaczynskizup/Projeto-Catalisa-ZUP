package api.comunidade.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

//Criar uma simples API de Doações para organizações sem fins lucrativos.


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doacao")
public class AlimentosDoadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_alimento", length = 50)
    private String tipoAlimento;

    @Column(name = "descricao_alimento", length = 100)
    private String descricaoAlimento;

    @Column(name = "quantidade_doacao")
    private int quantidadeDoacao;

    @Column(name = "tipo_unidade", length = 5)
    private String tipoUnidade;

    @Column(name = "data_doacao", length = 12)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDoacao;

    @ManyToOne
    @JoinColumn(name = "local_doador", referencedColumnName = "id")
    private LocalidadeDoadorModel localDoador;
}
