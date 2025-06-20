package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDTO>> listarPessoas(){
        List<PessoaDTO> pessoa = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        PessoaDTO pessoa = pessoaService.buscarPessoaPorId(id);
        if(pessoa != null){
            return ResponseEntity.ok(pessoa);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o id " + id + " nao existe nos nossos registros.");
        }

    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarPessoa(@RequestBody PessoaDTO pessoa){
        PessoaDTO pessoaDTO = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Pessoa criada com sucesso: " + pessoaDTO.getNome() + " (ID): " + pessoaDTO.getId());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada){
        PessoaDTO pessoa = pessoaService.atualizarPessoa(id,pessoaAtualizada);
        if(pessoa != null){
            return ResponseEntity.ok(pessoa);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pessoa com o id: " + id + " nao existe nos nossos registros");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id){
         if(pessoaService.buscarPessoaPorId(id) != null){
             pessoaService.deletarPessoaPorId(id);
             return ResponseEntity.ok("Pessoa com o ID " + id + " deletado com sucesso!");
         } else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("A pessoa com o id " + id + " nao existe nos nossos registros.");
         }
    }

    @GetMapping("/idade/{id}")
    public ResponseEntity<String> mostrarIdade(@PathVariable Long id){
        PessoaDTO pessoa = pessoaService.buscarPessoaPorId(id);
        if(pessoa != null){
            return ResponseEntity.ok("A pessoa tem " + pessoaService.mostrarIdade(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A pessoa com o id " + id + " nao existe nos nossos registros.");
        }
    }
}
