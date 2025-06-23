package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa os dados de uma Pessoa para transferência de dados.")
public class PessoaDTO {
    @Schema(description = "ID único da pessoa.", example = "1")
    private Long id;

    @Schema(description = "Nome completo da pessoa.", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Data de nascimento da pessoa.", example = "1990-08-15")
    private LocalDate dataNascimento;

    @Schema(description = "CPF da pessoa (sem pontos ou traços).", example = "12345678901", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;

    @Schema(description = "Lista de endereços associados à pessoa.")
    private List<EnderecoDTO> enderecos = new ArrayList<>();
}
