package api.comunidade.service;


import api.comunidade.entity.LocalidadeDoadorModel;
import api.comunidade.entity.dto.LocalidadeDoadorDTO;
import api.comunidade.repository.LocalidadeDoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalidadeDoadorService {

    @Autowired
    private LocalidadeDoadorRepository localidadeDoadorRepository;


    //Método para validar se os dados estão preenchidos e cadastrar os dados dos Doadores
    public LocalidadeDoadorModel cadastrarLocalidadeDoador(LocalidadeDoadorModel localidadeDoadorModel) {
        validarDadosDoadoresCadastrados(localidadeDoadorModel);

        return localidadeDoadorRepository.save(localidadeDoadorModel);
    }

    //Método da validação dos dados dos Doadores
    private void validarDadosDoadoresCadastrados(LocalidadeDoadorModel localidadeDoador) {
        if (localidadeDoador.getDoadorNome() == null || localidadeDoador.getDoadorNome().trim().isEmpty()) {
            throw new RuntimeException("O nome do doador não pode ser vazio. É obrigatório preencher todos os campos. Grato por participar!");
        } else if (localidadeDoador.getDoadorDocumento() == null || localidadeDoador.getDoadorDocumento().trim().isEmpty()) {
            throw new RuntimeException("O documento do doador não pode ser vazio. É obrigatório preencher todos os campos. Grato por participar!");
        } else if (localidadeDoador.getObservacoes() == null || localidadeDoador.getObservacoes().trim().isEmpty()) {
            throw new RuntimeException("Observações não pode ser vazio. É obrigatório preencher todos os campos. Grato por participar!");
        } else if (localidadeDoador.getEnderecoLocal() == null || localidadeDoador.getEnderecoLocal().trim().isEmpty()) {
            throw new RuntimeException("Endereço não pode ser vazio. É obrigatório preencher todos os campos. Grato por participar!");
        }

    }

    //Método para listar todos os Doadores
    public List<LocalidadeDoadorModel> listarLocalidadeDoador() {
        return localidadeDoadorRepository.findAll();
    }

    //Método para listar os dados de todos Doadores, conforme classe LocalidadeDoadorDTO
    public List<LocalidadeDoadorDTO> listarLocalidadeDoadorDTO() {
        List<LocalidadeDoadorModel> localidadeDoador = localidadeDoadorRepository.findAll();
        List<LocalidadeDoadorDTO> DoadosDTOList = new ArrayList<>();

        for (LocalidadeDoadorModel localidadeDoadorModel : localidadeDoador) {
            LocalidadeDoadorDTO doadosDTO = new LocalidadeDoadorDTO();
            doadosDTO.setDoadorNome(localidadeDoadorModel.getDoadorNome());
            doadosDTO.setObservacoes(localidadeDoadorModel.getObservacoes());
            doadosDTO.setEnderecoLocal(localidadeDoadorModel.getEnderecoLocal());

            DoadosDTOList.add(doadosDTO);
        }
        return DoadosDTOList;
    }

    //Método para buscar os dados de um Doador por id
    public LocalidadeDoadorModel buscarLocalidadeDoadorPorId(Long id) {
        return localidadeDoadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localidade doador não encontrado com ID: " + id));
    }

    //Método para atualizar os dados de um Doador
    public void atualizarLocalidadeDoador(Long id, LocalidadeDoadorModel localidadeDoador) {

        LocalidadeDoadorModel localidadeDoadorCadastrada = buscarLocalidadeDoadorPorId(id);
        if (localidadeDoadorCadastrada != null) {
            localidadeDoadorCadastrada.setId(id);

            if (localidadeDoador.getDoadorNome() != null) {
                localidadeDoadorCadastrada.setDoadorNome(localidadeDoador.getDoadorNome());
            }
            if (localidadeDoador.getDoadorDocumento() != null) {
                localidadeDoadorCadastrada.setDoadorDocumento(localidadeDoador.getDoadorDocumento());
            }
            if (localidadeDoador.getObservacoes() != null) {
                localidadeDoadorCadastrada.setObservacoes(localidadeDoador.getObservacoes());
            }
            if (localidadeDoador.getEnderecoLocal() != null) {
                localidadeDoadorCadastrada.setEnderecoLocal(localidadeDoador.getEnderecoLocal());
            }

        } else {

            throw new RuntimeException("Localidade doador não encontrado com ID: " + id);
        }

        localidadeDoadorRepository.save(localidadeDoadorCadastrada);

    }

    //Método para deletar os dados de um Doador
    public void deletarLocalidadeDoador(Long id) {
        LocalidadeDoadorModel doadorCadastrado = buscarLocalidadeDoadorPorId(id);
        localidadeDoadorRepository.delete(doadorCadastrado);
    }

}
