package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_pessoas")
@NoArgsConstructor
@AllArgsConstructor
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nome")
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @Column(name = "Data_Nascimento")
    private LocalDate dataNascimento;

    @Column(unique = true)
    @NotBlank(message = "O CPF é obrigatório!")
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "enderecos_id")
    private EnderecoModel enderecos;
}