package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PessoaMapper {
    @Autowired
    private EnderecoMapper enderecoMapper;

    public PessoaModel map(PessoaDTO pessoaDTO){
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(pessoaDTO.getId());
        pessoaModel.setNome(pessoaDTO.getNome());
        pessoaModel.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoaModel.setCpf(pessoaDTO.getCpf());
        if(pessoaDTO.getEnderecos() != null){
            pessoaModel.setEnderecos(
                    pessoaDTO.getEnderecos().stream()
                            .map(enderecoMapper::map)
                            .collect(Collectors.toList())
            );
            pessoaModel.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoaModel));
        }

        return pessoaModel;
    }

    public PessoaDTO map(PessoaModel pessoaModel){
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoaModel.getId());
        pessoaDTO.setNome(pessoaModel.getNome());
        pessoaDTO.setDataNascimento(pessoaModel.getDataNascimento());
        pessoaDTO.setCpf(pessoaModel.getCpf());
        if (pessoaModel.getEnderecos() != null) {
            pessoaDTO.setEnderecos(
                    pessoaModel.getEnderecos().stream()
                            .map(enderecoMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return pessoaDTO;
    }

}
