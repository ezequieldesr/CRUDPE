package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaModel> listarPessoas(){
        return pessoaRepository.findAll();
    }

    public PessoaModel buscarPessoaPorId(Long id){
        Optional<PessoaModel> pessoaModel = pessoaRepository.findById(id);
        return pessoaModel.orElse(null);
    }

    public PessoaModel criarPessoa(PessoaModel pessoaModel){
        return pessoaRepository.save(pessoaModel);
    }

    public void deletarPessoaPorId(Long id){
        pessoaRepository.deleteById(id);
    }

    public int mostrarIdade(Long id){
        PessoaModel pessoaModel = buscarPessoaPorId(id);
        return Period.between(pessoaModel.getDataNascimento(), LocalDate.now()).getYears();
    }
}
