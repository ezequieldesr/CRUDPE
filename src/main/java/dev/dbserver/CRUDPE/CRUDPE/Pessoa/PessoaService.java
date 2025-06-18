package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaModel> listarPessoas(){
        return pessoaRepository.findAll();
    }
}
