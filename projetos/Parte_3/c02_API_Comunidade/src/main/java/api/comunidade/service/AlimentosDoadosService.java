package api.comunidade.service;


import api.comunidade.entity.AlimentosDoadosModel;
import api.comunidade.entity.dto.AlimentosDoadosDTO;
import api.comunidade.repository.AlimentosDoadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlimentosDoadosService {

    @Autowired
    private AlimentosDoadosRepository alimentosDoadosRepository;


    //Método para validar se os dados estão preenchidos e cadastrar alimentos doados
    public AlimentosDoadosModel cadastrarAlimentosDoados(AlimentosDoadosModel alimentosDoadosModel) {
        validarAlimentosDoadosCadastrados(alimentosDoadosModel);

        return alimentosDoadosRepository.save(alimentosDoadosModel);
    }

    //Método para listar todos os alimentos doados
    public List<AlimentosDoadosModel> listarAlimentosDoados() {
        return alimentosDoadosRepository.findAll();

    }

    //Método para listar os alimentos doados conforme classe AlimentosDoadosDTO
    public List<AlimentosDoadosDTO> listarAlimentosDoadosDTO() {
        List<AlimentosDoadosModel> alimentosDoadosModel = alimentosDoadosRepository.findAll();
        List<AlimentosDoadosDTO> DoadosDTOList = new ArrayList<>();

        for (AlimentosDoadosModel alimentosDoados : alimentosDoadosModel) {
            AlimentosDoadosDTO doadosDTO = new AlimentosDoadosDTO();
            doadosDTO.setDescricaoAlimento(alimentosDoados.getDescricaoAlimento());
            doadosDTO.setTipoAlimento(alimentosDoados.getTipoAlimento());
            doadosDTO.setQuantidadeDoacao(alimentosDoados.getQuantidadeDoacao());
            doadosDTO.setTipoUnidade(alimentosDoados.getTipoUnidade());

            DoadosDTOList.add(doadosDTO);
        }
        return DoadosDTOList;

    }

    //Método para buscar alimentos doados por id
    public AlimentosDoadosModel buscarAlimentosDoadosPorId(Long id) {
        return alimentosDoadosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimentos doados não encontrado com ID: " + id));

    }

    //Método para atualizar alimentos doados
    public void atualizarAlimentosDoados(Long id, AlimentosDoadosModel alimentosDoadosModel) {

        AlimentosDoadosModel alimentoCadastrado = buscarAlimentosDoadosPorId(id);
        if (alimentoCadastrado != null) {
            alimentoCadastrado.setId(id);

            if (alimentosDoadosModel.getDescricaoAlimento() != null) {
                alimentoCadastrado.setDescricaoAlimento(alimentosDoadosModel.getDescricaoAlimento());
            }
            if (alimentosDoadosModel.getTipoAlimento() != null) {
                alimentoCadastrado.setTipoAlimento(alimentosDoadosModel.getTipoAlimento());
            }
            if (alimentosDoadosModel.getQuantidadeDoacao() != 0.0) {
                alimentoCadastrado.setQuantidadeDoacao(alimentosDoadosModel.getQuantidadeDoacao());
            }
            if (alimentosDoadosModel.getTipoUnidade() != null) {
                alimentoCadastrado.setTipoUnidade(alimentosDoadosModel.getTipoUnidade());
            }
            if (alimentosDoadosModel.getDataDoacao() != null) {
                alimentoCadastrado.setDataDoacao(alimentosDoadosModel.getDataDoacao());
            }
            if (alimentosDoadosModel.getLocalDoador() != null) {
                alimentoCadastrado.setLocalDoador(alimentosDoadosModel.getLocalDoador());
            }

        } else {

            throw new RuntimeException("Alimentos doados não encontrado com ID: " + id);
        }

        alimentosDoadosRepository.save(alimentoCadastrado);
    }

    //Método para deletar alimentos doados
    public void deletarAlimentosDoados(Long id) {
        AlimentosDoadosModel alimentosDoados = buscarAlimentosDoadosPorId(id);
        alimentosDoadosRepository.delete(alimentosDoados);
    }

    //Método para validar o cadastro de alimentos doados. Verificando se os campos obrigatórios estão preenchidos corretamente.
    private void validarAlimentosDoadosCadastrados(AlimentosDoadosModel alimentosDoados) {
        if (alimentosDoados.getDescricaoAlimento() == null || alimentosDoados.getDescricaoAlimento().trim().isEmpty()) {
            throw new RuntimeException("A descrição do alimento é obrigatória.");
        } else if (alimentosDoados.getTipoAlimento() == null || alimentosDoados.getTipoAlimento().trim().isEmpty()) {
            throw new RuntimeException("O tipo do alimento é obrigatório.");
        } else if (alimentosDoados.getQuantidadeDoacao() == 0) {
            throw new RuntimeException("A quantidade do doação é obrigatória.");
        } else if (alimentosDoados.getTipoUnidade() == null || alimentosDoados.getTipoUnidade().trim().isEmpty()) {
            throw new RuntimeException("O tipo da unidade é obrigatório.");
        }
    }
}
