package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {
    public PessoaModel map(PessoaDTO pessoaDTO) {
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(pessoaDTO.getId());
        pessoaModel.setNome(pessoaDTO.getNome());
        pessoaModel.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoaModel.setCpf(pessoaDTO.getCpf());
        pessoaModel.setEnderecos(pessoaDTO.getEnderecos());

        return pessoaModel;
    }

    public PessoaDTO map(PessoaModel pessoaModel) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoaModel.getId());
        pessoaDTO.setNome(pessoaModel.getNome());
        pessoaDTO.setDataNascimento(pessoaModel.getDataNascimento());
        pessoaDTO.setCpf(pessoaModel.getCpf());
        pessoaDTO.setEnderecos(pessoaModel.getEnderecos());

        return pessoaDTO;
    }

}
