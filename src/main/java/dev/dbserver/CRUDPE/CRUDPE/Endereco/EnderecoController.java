package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/listar")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos(){
        return ResponseEntity.ok(enderecoService.listarEnderecos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.buscarEnderecoPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody EnderecoDTO endereco){
        EnderecoDTO enderecoCriado = enderecoService.criarEndereco(endereco);
        return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO endereco){
        EnderecoDTO enderecoAtualizado = enderecoService.atualizarEndereco(id, endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEndereco(@PathVariable Long id){
        enderecoService.deletarEndereco(id);
        return ResponseEntity.ok("Endereço com o ID " + id + " deletado com sucesso!");
    }

}
