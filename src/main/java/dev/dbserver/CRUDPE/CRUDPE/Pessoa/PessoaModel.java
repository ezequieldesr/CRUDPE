package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Data_Nascimento")
    private LocalDate dataNascimento;

    @Column(unique = true)
    private String cpf;


}