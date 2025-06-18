package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
}
