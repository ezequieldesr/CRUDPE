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
    @Operation(summary = "Lista todas as pessoas", description = "Retorna uma lista com todas as pessoas e seus respectivos endereços cadastrados.")
    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }
    @Operation(summary = "Busca uma pessoa por ID", description = "Retorna os dados de uma única pessoa com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada para o ID fornecido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPessoaPorId(id));
    }
    @Operation(summary = "Cria uma nova pessoa", description = "Cadastra uma nova pessoa e seus endereços na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/criar")
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza os dados de uma pessoa", description = "Atualiza os dados de uma pessoa existente com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada para o ID fornecido")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaAtualizada));
    }

    @Operation(summary = "Deleta uma pessoa", description = "Remove uma pessoa e todos os seus endereços associados da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada para o ID fornecido")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoaPorId(id);
        return ResponseEntity.ok("Pessoa com o ID " + id + " deletada com sucesso!");
    }

    @Operation(summary = "Mostrar a idade da Pessoa", description = "Mostra a idade da pessoa que esta associada na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A pessoa tem x anos de idade"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada para o ID fornecido")
    })
    @GetMapping("/idade/{id}")
    public ResponseEntity<String> mostrarIdade(@PathVariable Long id) {
        PessoaDTO pessoa = pessoaService.buscarPessoaPorId(id);
        int idade = pessoaService.mostrarIdade(id);
        return ResponseEntity.ok(pessoa.getNome() + " tem " + idade + " anos de idade");
    }
}
