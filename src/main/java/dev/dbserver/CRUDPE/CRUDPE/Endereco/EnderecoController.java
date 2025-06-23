package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/enderecos")
@Tag(name = "Endereços", description = "Endpoints para Gerenciamento de Endereços")

public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Cria um novo endereço", description = "Cadastra um novo endereço e o associa a uma pessoa existente através do `pessoaId`.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado e associado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não é possível criar o endereço. Pessoa com o id não encontrada.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Não é possível criar o endereço. Pessoa com o id 101 não encontrada")))
    })
    @PostMapping("/criar")
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody EnderecoDTO endereco) {
        EnderecoDTO enderecoCriado = enderecoService.criarEndereco(endereco);
        return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
    }


    @Operation(summary = "Lista todos os endereços", description = "Retorna uma lista com todos os endereços cadastrados no sistema.")
    @GetMapping("/listar")

    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        return ResponseEntity.ok(enderecoService.listarEnderecos());
    }

    @Operation(summary = "Busca um endereço por ID", description = "Retorna os dados de um único endereço com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço com o id não encontrado.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Endereço com o id 101 não encontrado.")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarPorId(@Parameter(description = "ID do endereço a ser buscado.", required = true, example = "101") @PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.buscarEnderecoPorId(id));
    }


    @Operation(summary = "Atualiza os dados de um endereço", description = "Atualiza os dados de um endereço existente com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso", content = @Content(mediaType = "*/*", schema = @Schema(type = "Json", example = "{\"id\": 101,\n" +
                    "  \"rua\": \"Avenida Bertoldo Hames\",\n" +
                    "  \"numero\": 100,\n" +
                    "  \"bairro\": \"Bela Vista\",\n" +
                    "  \"cidade\": \"Santa Catarina\",\n" +
                    "  \"estado\": \"SC\",\n" +
                    "  \"cep\": \"00999999\",\n" +
                    "  \"pessoaId\": 1}"))),
            @ApiResponse(responseCode = "404", description = "Endereço com o id não encontrado para atualização.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Endereço com o id 1 não encontrado para atualização.")))
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@Parameter(description = "ID do endereço a ser atualizado.", required = true, example = "102")
                                                         @PathVariable Long id, @RequestBody EnderecoDTO endereco) {
        EnderecoDTO enderecoAtualizado = enderecoService.atualizarEndereco(id, endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @Operation(summary = "Deleta um endereço", description = "Remove um endereço da base de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço deletado com sucesso",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Endereço com o ID 1 deletado com sucesso!"))),

            @ApiResponse(responseCode = "404", description = "Endereço com o ID não encontrado para deleção.",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Endereço com o id 1 não encontrado para deleção.")))
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEndereco(@Parameter(description = "ID do endereço a ser deletado.", required = true, example = "1")
                                                  @PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.ok("Endereço com o ID " + id + " deletado com sucesso!");
    }

}
