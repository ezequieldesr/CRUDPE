package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;

    public List<PessoaDTO> listarPessoas(){
        List<PessoaModel> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(pessoaMapper::map)
                .collect(Collectors.toList());
    }

    public PessoaDTO buscarPessoaPorId(Long id){
        Optional<PessoaModel> pessoaModel = pessoaRepository.findById(id);
        return pessoaModel.map(pessoaMapper::map).orElse(null);
    }

    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO){
        PessoaModel pessoa = pessoaMapper.map(pessoaDTO);
        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.map(pessoa);
    }

    public PessoaDTO atualizarPessoa(Long id, PessoaDTO pessoaDTO){
        Optional<PessoaModel> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            PessoaModel pessoaAtualizada = pessoaMapper.map(pessoaDTO);
            pessoaAtualizada.setId(id);
            PessoaModel pessoaSalva = pessoaRepository.save(pessoaAtualizada);
            return pessoaMapper.map(pessoaSalva);
        }
        return null;
    }

    public void deletarPessoaPorId(Long id){
        pessoaRepository.deleteById(id);
    }

    public int mostrarIdade(Long id){
        PessoaDTO pessoaDTO = buscarPessoaPorId(id);
        return Period.between(pessoaDTO.getDataNascimento(), LocalDate.now()).getYears();
    }
}
