package estoque.app.controller;


import estoque.app.exceptions.FornecedorNotFound;
import estoque.app.model.FornecedorModel;
import estoque.app.model.dto.FornecedorListDTO;
import estoque.app.service.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@Tag(name = "Fornecedores", description = "Endpoint para controle de fornecedores.")
public class FornecedorController {

    private FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    //Endpoint para cadastrar um novo fornecedor
    @PostMapping
    @Operation(summary = "Cadastra um novo fornecedor.",
            description = "Endpoint para cadastrar um novo fornecedor.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fornecedor cadastrado com sucesso")})
    public FornecedorModel cadastrarFornecedor(@RequestBody FornecedorModel fornecedorModel) {
        return fornecedorService.cadastrarFornecedor(fornecedorModel);
    }

    //Endpoint para listar todos os fornecedores
    @GetMapping
    @Operation(summary = "Lista todos os fornecedores.",
            description = "Endpoint para listar todos os fornecedores.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedores listados com sucesso")})
    public List<FornecedorModel> listarTodosFornecedores() {
        return fornecedorService.listarTodosFornecedores();
    }

    //Endpoint para listar um fornecedor por id
    @GetMapping("/{id}")
    @Operation(summary = "Lista um fornecedor por id.",
            description = "Endpoint para listar um fornecedor por id.",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor listado com sucesso")})
    public FornecedorModel listarFornecedorPorId(@PathVariable Long id) {
        FornecedorModel fornecedorExistente = fornecedorService.listarFornecedorPorId(id);

        if (fornecedorExistente == null) {
            throw new FornecedorNotFound(id);
        }
        return fornecedorExistente;
    }

    //Endpoint para atualizar um fornecedor
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza qualquer dado um fornecedor cadastrado por id.",
            description = "Endpoint para atualizar um fornecedor por id" +
                    "Pode preencher para modificar todos os campos ou apenas alguns ou somente um."
            , method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso")})
    public FornecedorModel atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorModel fornecedorModel) {
        FornecedorModel fornecedorExistente = fornecedorService.listarFornecedorPorId(id);
        if (fornecedorExistente != null) {
            fornecedorExistente.setId(id);

            if (fornecedorModel.getCnpj() != null) {
                fornecedorExistente.setCnpj(fornecedorModel.getCnpj());
            }
            if (fornecedorModel.getRazaoSocial() != null) {
                fornecedorExistente.setRazaoSocial(fornecedorModel.getRazaoSocial());
            }
            if (fornecedorModel.getNomeFantasia() != null) {
                fornecedorExistente.setNomeFantasia(fornecedorModel.getNomeFantasia());
            }
            if (fornecedorModel.getEmail() != null) {
                fornecedorExistente.setEmail(fornecedorModel.getEmail());
            }
            if (fornecedorModel.getCategoriaProduto() != null) {
                fornecedorExistente.setCategoriaProduto(fornecedorModel.getCategoriaProduto());
            }
            if (fornecedorModel.getNomeProduto() != null) {
                fornecedorExistente.setNomeProduto(fornecedorModel.getNomeProduto());
            }
            if (fornecedorModel.getNomeContato() != null) {
                fornecedorExistente.setNomeContato(fornecedorModel.getNomeContato());
            }
            if (fornecedorModel.getTelefone() != null) {
                fornecedorExistente.setTelefone(fornecedorModel.getTelefone());
            }
            if (fornecedorModel.getEndereco() != null) {
                fornecedorExistente.setEndereco(fornecedorModel.getEndereco());
            }

        } else {

            throw new FornecedorNotFound(id);
        }
        return fornecedorService.atualizarFornecedor(fornecedorExistente);
    }

    //Endpoint para deletar um fornecedor
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um fornecedor por id.",
            description = "Endpoint para deletar um fornecedor.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor deletado com sucesso")})
    public void deletarFornecedor(@PathVariable Long id) {
        fornecedorService.deletarFornecedor(id);
    }

    //Aqui Endpoint para listar um fornecedor conforme DTO
    @GetMapping("/dto")
    @Operation(summary = "Listagem de todos os fornecedores, conforme limitações do DTO",
            description = "Endpoint para listar um fornecedor por id" + "\n" +
                    "Só será retornado os campos id, cnpj, razaoSocial e nomeFantasia."
            , method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedores listados com sucesso")
    })
    public List<FornecedorListDTO> listarTodosFornecedoresDTO() {
        List<FornecedorModel> fornecedores = fornecedorService.listarTodosFornecedoresDTO();
        List<FornecedorListDTO> dtos = new ArrayList<>();

        for (FornecedorModel fornecedor : fornecedores) {
            FornecedorListDTO dto = new FornecedorListDTO();
            dto.setId(fornecedor.getId());
            dto.setCnpj(fornecedor.getCnpj());
            dto.setRazaoSocial(fornecedor.getRazaoSocial());
            dto.setNomeFantasia(fornecedor.getNomeFantasia());
            dtos.add(dto);
        }
        return dtos;
    }
}
