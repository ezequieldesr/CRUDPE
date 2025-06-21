package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private List<EnderecoModel> enderecos = new ArrayList<>();
}
