package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoMapper;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.Domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PessoaMapper {
    @Autowired
    private EnderecoMapper enderecoMapper;

    public Pessoa map(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoa.setCpf(pessoaDTO.getCpf());
        if(pessoaDTO.getEnderecos() != null){
            pessoa.setEnderecos(
                    pessoaDTO.getEnderecos().stream()
                            .map(enderecoMapper::map)
                            .collect(Collectors.toList())
            );
            pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        }

        return pessoa;
    }

    public PessoaDTO map(Pessoa pessoa){
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDataNascimento(pessoa.getDataNascimento());
        pessoaDTO.setCpf(pessoa.getCpf());
        if (pessoa.getEnderecos() != null) {
            pessoaDTO.setEnderecos(
                    pessoa.getEnderecos().stream()
                            .map(enderecoMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return pessoaDTO;
    }

}
