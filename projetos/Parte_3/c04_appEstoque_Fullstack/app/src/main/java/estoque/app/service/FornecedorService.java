package estoque.app.service;


import estoque.app.model.FornecedorModel;
import estoque.app.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public FornecedorModel cadastrarFornecedor(FornecedorModel fornecedorModel) {
        return fornecedorRepository.save(fornecedorModel);
    }

    public List<FornecedorModel> listarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    public FornecedorModel listarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id).orElse(null);
    }

    public FornecedorModel atualizarFornecedor(FornecedorModel fornecedorModel) {
        return fornecedorRepository.save(fornecedorModel);
    }

    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }

    //Método para listar um fornecedor conforme DTO
    public List<FornecedorModel> listarTodosFornecedoresDTO() {
        return fornecedorRepository.findAll();
    }
}
