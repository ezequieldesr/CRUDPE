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
    private PessoaMapper pessoaMapper;

    public List<PessoaModel> listarPessoas(){
        return pessoaRepository.findAll();
    }

    public PessoaModel buscarPessoaPorId(Long id){
        Optional<PessoaModel> pessoaModel = pessoaRepository.findById(id);
        return pessoaModel.orElse(null);
    }

    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO){
        PessoaModel pessoa = pessoaMapper.map(pessoaDTO);
        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.map(pessoa);
    }

    public PessoaModel atualizarPessoa(Long id, PessoaModel pessoaAtualizada){
        if(pessoaRepository.existsById(id)){
            pessoaAtualizada.setId(id);
            return pessoaRepository.save(pessoaAtualizada);
        }
        return null;
    }

    public void deletarPessoaPorId(Long id){
        pessoaRepository.deleteById(id);
    }

    public int mostrarIdade(Long id){
        PessoaModel pessoaModel = buscarPessoaPorId(id);
        return Period.between(pessoaModel.getDataNascimento(), LocalDate.now()).getYears();
    }
}
