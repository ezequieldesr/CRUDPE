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
        List<EnderecoDTO> endereco = enderecoService.listarEnderecos();
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        EnderecoDTO enderecoDTO = enderecoService.buscarEnderecoPorId(id);
        if(enderecoDTO != null){
            return ResponseEntity.ok(enderecoDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O endereco com o id " + enderecoDTO.getId() + " nao existe nos nossos registros");
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarEndereco(@RequestBody EnderecoDTO endereco){
        EnderecoDTO enderecoDTO = enderecoService.criarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Endereco criado com sucesso! (ID): " + enderecoDTO.getId());
    }

    @PutMapping("/atualizar")
    public String atualizarEndereco(){
        return "Atualizar pessoa com sucesso";
    }

    @DeleteMapping("/deletar")
    public String deletarEndereco(){
        return "Deletar pessoa com sucesso";
    }

}
