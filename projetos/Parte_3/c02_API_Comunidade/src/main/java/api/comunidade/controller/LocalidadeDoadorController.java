package api.comunidade.controller;


import api.comunidade.entity.LocalidadeDoadorModel;
import api.comunidade.entity.dto.LocalidadeDoadorDTO;
import api.comunidade.service.LocalidadeDoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doadores")
public class LocalidadeDoadorController {

    @Autowired
    private LocalidadeDoadorService localidadeDoadorService;

    //Cadastrar dados de doadores
    @PostMapping
    public ResponseEntity<String> cadastrarLocalidadeDoador(@RequestBody LocalidadeDoadorModel localidadeDoadorModel) {
        localidadeDoadorService.cadastrarLocalidadeDoador(localidadeDoadorModel);
        return new ResponseEntity<>("Cadastro de Doador realizado com sucesso!", HttpStatus.CREATED);
    }

    //Listar todos os dados de doadores
    @GetMapping
    public ResponseEntity<List<LocalidadeDoadorModel>> listarLocalidadeDoador() {

        List<LocalidadeDoadorModel> localidadeDoador = localidadeDoadorService.listarLocalidadeDoador();

        return ResponseEntity.ok(localidadeDoador);
    }

    //Listar os dados de doadores conforme classe LocalidadeDoadorDTO
    @GetMapping("/dto")
    public ResponseEntity<List<LocalidadeDoadorDTO>> listarLocalidadeDoadorDTO() {
        List<LocalidadeDoadorDTO> localidadeDoadorDTO = localidadeDoadorService.listarLocalidadeDoadorDTO();
        return ResponseEntity.ok(localidadeDoadorDTO);
    }

    //Listar os dados de doadores por id
    @GetMapping("/{id}")
    public ResponseEntity<LocalidadeDoadorModel> buscarLocalidadeDoadorPorId(@PathVariable Long id) {
        LocalidadeDoadorModel localidadeDoador = localidadeDoadorService.buscarLocalidadeDoadorPorId(id);
        return ResponseEntity.ok(localidadeDoador);
    }

    //Atualizar dados de doadores
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarLocalidadeDoador(@PathVariable Long id,
                                                            @RequestBody LocalidadeDoadorModel localidadeDoadorModel) {

        localidadeDoadorService.atualizarLocalidadeDoador(id, localidadeDoadorModel);
        return new ResponseEntity<>("Cadastro de Doador realizado com sucesso!", HttpStatus.CREATED);

    }

    //Deletar dados de doadores
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLocalidadeDoador(@PathVariable Long id) {
        localidadeDoadorService.deletarLocalidadeDoador(id);
        return new ResponseEntity<>("Cadastro deletado com sucesso!", HttpStatus.OK);
    }
}
