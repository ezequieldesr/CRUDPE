package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@Tag(name = "Pessoas", description = "Endpoints para Gerenciamento de Pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/criar")
    @Operation(summary = "01. Cria uma nova pessoa", description = "Cadastra uma nova pessoa e seus endereços na base de dados.")
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }


    @GetMapping("/listar")
    @Operation(summary = "02. Lista todas as pessoas", description = "Retorna uma lista com todas as pessoas e seus respectivos endereços cadastrados.")
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "03. Busca uma pessoa por ID", description = "Retorna os dados de uma única pessoa com base no seu ID.")
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPessoaPorId(id));
    }


    @PutMapping("/atualizar/{id}")
    @Operation(summary = "04. Atualiza os dados de uma pessoa", description = "Atualiza os dados de uma pessoa existente com base no seu ID.")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaAtualizada));
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "05. Deleta uma pessoa", description = "Remove uma pessoa e todos os seus endereços associados da base de dados.")
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoaPorId(id);
        return ResponseEntity.ok("Pessoa com o ID " + id + " deletada com sucesso!");
    }

    @GetMapping("/idade/{id}")
    @Operation(summary = "06. Mostrar a idade da Pessoa", description = "Mostra a idade da pessoa que esta associada na base de dados.")
    public ResponseEntity<String> mostrarIdade(@PathVariable Long id) {
        PessoaDTO pessoa = pessoaService.buscarPessoaPorId(id);
        int idade = pessoaService.mostrarIdade(id);
        return ResponseEntity.ok(pessoa.getNome() + " tem " + idade + " anos de idade");
    }
}
