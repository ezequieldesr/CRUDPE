package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import dev.dbserver.CRUDPE.CRUDPE.Pessoa.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private PessoaModel pessoas;
}
