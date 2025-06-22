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
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPessoaPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaAtualizada));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoaPorId(id);
        return ResponseEntity.ok("Pessoa com o ID " + id + " deletada com sucesso!");
    }

    @GetMapping("/idade/{id}")
    public ResponseEntity<String> mostrarIdade(@PathVariable Long id) {
        PessoaDTO pessoa = pessoaService.buscarPessoaPorId(id);
        int idade = pessoaService.mostrarIdade(id);
        return ResponseEntity.ok(pessoa.getNome() + " tem " + idade + " anos de idade");
    }
}
