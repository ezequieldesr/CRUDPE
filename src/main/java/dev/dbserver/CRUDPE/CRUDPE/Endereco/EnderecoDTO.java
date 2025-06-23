package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa os dados de um Endereço para transferência de dados.")
public class EnderecoDTO {
    @Schema(description = "ID único do endereço.", example = "101")
    private Long id;
    @Schema(description = "Nome da rua.", example = "Avenida Paulista")
    private String rua;

    @Schema(description = "Número do imóvel.", example = "1500")
    private int numero;

    @Schema(description = "Bairro.", example = "Bela Vista")
    private String bairro;

    @Schema(description = "Cidade.", example = "São Paulo")
    private String cidade;

    @Schema(description = "Sigla do estado.", example = "SP")
    private String estado;

    @Schema(description = "CEP (sem pontos ou traços).", example = "01310200")
    private String cep;

    @Schema(description = "ID da pessoa a qual este endereço pertence.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long pessoaId;

}
