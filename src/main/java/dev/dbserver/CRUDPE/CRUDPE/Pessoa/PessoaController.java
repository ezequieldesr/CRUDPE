package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "Cria uma nova pessoa", description = "Cadastra uma nova pessoa e seus endereços na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso!"),
    })
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }


    @GetMapping("/listar")
    @Operation(summary = "Lista todas as pessoas", description = "Retorna uma lista com todas as pessoas e seus respectivos endereços cadastrados.")
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma pessoa por ID", description = "Retorna os dados de uma única pessoa com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Pessoa com o id não encontrada.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Pessoa com o id 1 não encontrada.")))
    })
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPessoaPorId(id));
    }


    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualiza os dados de uma pessoa", description = "Atualiza os dados de uma pessoa existente com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso", content = @Content(mediaType = "*/*", schema = @Schema(type = "Json", example = "{\n" +
                    "  \"id\": 1,\n" +
                    "  \"nome\": \"Bertoldo Honorato\",\n" +
                    "  \"dataNascimento\": \"1930-10-25\",\n" +
                    "  \"cpf\": \"1232342378901\",\n" +
                    "  \"enderecos\": [\n" +
                    "    {\n" +
                    "      \"id\": 101,\n" +
                    "      \"rua\": \"Avenida Paulista\",\n" +
                    "      \"numero\": 1500,\n" +
                    "      \"bairro\": \"Bela Vista\",\n" +
                    "      \"cidade\": \"São Paulo\",\n" +
                    "      \"estado\": \"SP\",\n" +
                    "      \"cep\": \"01310200\",\n" +
                    "      \"pessoaId\": 1\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"))),
            @ApiResponse(responseCode = "404", description = "Pessoa com o id não encontrado para atualização.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Pessoa com o id 1 não encontrado para atualização.")))
    })
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaAtualizada));
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma pessoa", description = "Remove uma pessoa e todos os seus endereços associados da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso!",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Pessoa com o ID 1 deletada com sucesso!"))),
            @ApiResponse(responseCode = "404", description = "Pessoa com o id não encontrado para deleção.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Pessoa com o id 1 não encontrada para deleção.")))
    })
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoaPorId(id);
        return ResponseEntity.ok("Pessoa com o ID " + id + " deletada com sucesso!");
    }

    @GetMapping("/idade/{id}")
    @Operation(summary = "Mostrar a idade da Pessoa", description = "Mostra a idade da pessoa que esta associada na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Idade da pessoa mostrada com sucesso!",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "João da Silva tem 34 anos de idade"))),
            @ApiResponse(responseCode = "404", description = "Pessoa com o id não encontrada.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Pessoa com o id 1 não encontrada.")))
    })
    public ResponseEntity<String> mostrarIdade(@PathVariable Long id) {
        PessoaDTO pessoa = pessoaService.buscarPessoaPorId(id);
        int idade = pessoaService.mostrarIdade(id);
        return ResponseEntity.ok(pessoa.getNome() + " tem " + idade + " anos de idade");
    }
}
