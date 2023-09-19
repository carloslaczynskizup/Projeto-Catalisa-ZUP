package api.comunidade.controller;

import api.comunidade.entity.AlimentosDoadosModel;
import api.comunidade.entity.dto.AlimentosDoadosDTO;
import api.comunidade.service.AlimentosDoadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentosDoados")
public class AlimentosDoadosController {

    @Autowired
    private AlimentosDoadosService alimentosDoadosService;


    //Cadastrar alimentos doados
    @PostMapping
    public ResponseEntity<String> cadastrarAlimentosDoados(@RequestBody AlimentosDoadosModel alimentosDoadosModel) {

        alimentosDoadosService.cadastrarAlimentosDoados(alimentosDoadosModel);

        return new ResponseEntity<>("Cadastro de Alimentos realizados com sucesso!", HttpStatus.CREATED);

    }

    //Listar todos os alimentos doados

    @GetMapping
    public ResponseEntity<List<AlimentosDoadosModel>> listarAlimentosDoados() {

        List<AlimentosDoadosModel> alimentosDoados = alimentosDoadosService.listarAlimentosDoados();

        return ResponseEntity.ok(alimentosDoados);

    }

    //Listar os alimentos doados conforme classe AlimentosDoadosDTO
    @GetMapping("/dto")
    public ResponseEntity<List<AlimentosDoadosDTO>> listarAlimentosDoadosDTO() {
        List<AlimentosDoadosDTO> alimentosDoadosDTO = alimentosDoadosService.listarAlimentosDoadosDTO();
        return ResponseEntity.ok(alimentosDoadosDTO);
    }

    //Buscar alimentos doados por id

    @GetMapping("/{id}")
    public ResponseEntity<AlimentosDoadosModel> buscarAlimentosDoadosPorId(@PathVariable Long id) {
        AlimentosDoadosModel alimentosDoados = alimentosDoadosService.buscarAlimentosDoadosPorId(id);
        return ResponseEntity.ok(alimentosDoados);
    }

    //Atualizar alimentos doados por id
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAlimentosDoados(@PathVariable Long id,
                                                           @RequestBody AlimentosDoadosModel alimentosDoadosModel) {
        alimentosDoadosService.atualizarAlimentosDoados(id, alimentosDoadosModel);

        return new ResponseEntity<>("Cadastro atualizado com sucesso!", HttpStatus.OK);

    }

    //Deletar alimentos doados
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAlimentosDoados(@PathVariable Long id) {
        alimentosDoadosService.deletarAlimentosDoados(id);
        return new ResponseEntity<>("Cadastro deletado com sucesso!", HttpStatus.OK);
    }

}
