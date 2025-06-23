package dev.dbserver.CRUDPE.CRUDPE.Pessoa.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.dbserver.CRUDPE.CRUDPE.Endereco.Domain.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_pessoas")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

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

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();
}